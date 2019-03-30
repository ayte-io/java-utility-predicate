package io.ayte.utility.predicate.kit.unary.delegate.collection;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.unary.standard.Identity;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllOfTest {
    @Test
    public void failsIfAnyOfDelegatesFail() {
        val sut = AllOf.create(Arrays.asList(any -> true, any -> false));
        assertFalse(sut.test(null));
    }

    @Test
    public void returnsTrueIfAllDelegatesSucceed() {
        val sut = AllOf.create(Arrays.asList(any -> true, any -> true));
        assertTrue(sut.test(null));
    }

    @Test
    public void returnsConstantTrueOnEmptyDelegates() {
        val sut = AllOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> AllOf.create(null));
    }

    @Test
    public void unwrapsNestedAllOf() {
        Predicate<Object> delegate = any -> true;
        val alpha = AllOf.create(Arrays.asList(delegate, delegate));
        val beta = AllOf.create(Arrays.asList(delegate, alpha));
        assertThat(beta, instanceOf(AllOf.class));
        assertThat(((AllOf) beta).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }

    @Test
    public void shortcutsOnConstantFalse() {
        val sut = AllOf.create(Arrays.asList(ConstantFalse.create(), any -> true));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void stripsConstantTrue() {
        val delegate = Identity.create();
        val sut = AllOf.create(Arrays.asList(delegate, delegate, ConstantTrue.<Boolean>create()));
        assertThat(sut, instanceOf(AllOf.class));
        assertThat(((AllOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void passesSingleDelegateThrough() {
        val delegate = Identity.create();
        val sut = AllOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }
}
