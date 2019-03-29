package io.ayte.utility.predicate.kit.unary.delegate.collection;

import io.ayte.utility.predicate.kit.unary.delegate.Not;
import io.ayte.utility.predicate.kit.unary.delegate.collection.NoneOf;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.unary.standard.Identity;
import lombok.val;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoneOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> NoneOf.create(null));
    }

    @Test
    public void returnsConstantTrueOnEmptyDelegates() {
        val sut = NoneOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void returnsFalseIfAnyDelegateReturnsTrue() {
        val sut = NoneOf.create(Arrays.asList(any -> false, any -> false, any -> true));
        assertFalse(sut.test(null));
    }

    @Test
    public void returnsTrueIfAllDelegatesReturnFalse() {
        val sut = NoneOf.create(Arrays.asList(any -> false, any -> false));
        assertTrue(sut.test(null));
    }

    @Test
    public void unwrapsNestedNoneOf() {
        Predicate<Object> delegate = any -> false;
        val alpha = NoneOf.create(Arrays.asList(delegate, delegate));
        val beta = NoneOf.create(Arrays.asList(alpha, delegate));
        assertThat(beta, instanceOf(NoneOf.class));
        assertThat(((NoneOf) beta).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }

    @Test
    public void stripsConstantFalse() {
        Predicate<Object> delegate = any -> false;
        val sut = NoneOf.create(Arrays.asList(delegate, ConstantFalse.create(), delegate));
        assertThat(sut, instanceOf(NoneOf.class));
        assertThat(((NoneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortcutsOnConstantTrue() {
        val sut = NoneOf.create(Arrays.asList(any -> false, ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void shortcutsSingleDelegate() {
        val delegate = Identity.create();
        val sut = NoneOf.create(Collections.singleton(delegate));
        MatcherAssert.assertThat(sut, equalTo(Not.create(delegate)));
    }
}
