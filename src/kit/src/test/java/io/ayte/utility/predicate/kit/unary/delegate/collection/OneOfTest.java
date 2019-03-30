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

class OneOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> OneOf.create(null));
    }

    @Test
    public void returnsConstantFalseOnEmptyDelegates() {
        val sut = OneOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void returnsFalseIfNoDelegatesMatch() {
        val sut = OneOf.create(Arrays.asList(any -> false, any -> false, any -> false));
        assertFalse(sut.test(null));
    }

    @Test
    public void returnsTrueIfSingleDelegateMatches() {
        val sut = OneOf.create(Arrays.asList(any -> true, any -> false, any -> false));
        assertTrue(sut.test(null));
    }

    @Test
    public void returnsFalseIfSeveralDelegatesMatch() {
        val sut = OneOf.create(Arrays.asList(any -> true, any -> true, any -> true));
        assertFalse(sut.test(null));
    }

    @Test
    public void stripsConstantFalse() {
        Predicate<Object> delegate = any -> true;
        val sut = OneOf.create(Arrays.asList(delegate, delegate, ConstantFalse.create()));
        assertThat(sut, instanceOf(OneOf.class));
        assertThat(((OneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortcutsOnTwoOrMoreConstantTrues() {
        val sut = OneOf.create(Arrays.asList(ConstantTrue.create(), ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void wrapsSingleDelegate() {
        val delegate = Identity.create();
        val sut = OneOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }
}
