package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.ternary.standard.UsingFirst;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class AnyOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> AnyOf.create(null));
    }

    @Test
    public void returnsConstantFalseOnEmptyDelegates() {
        val sut = AnyOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void returnsSingleDelegate() {
        val delegate = UsingFirst.create();
        val sut = AnyOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }

    @Test
    public void returnsTrueIfAnyDelegateReturnsTrue() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(null, null, null)).thenReturn(false, true, false);
        val sut = AnyOf.create(Arrays.asList(delegate, delegate, delegate));
        assertTrue(sut.test(null, null, null));
        verify(delegate, times(2)).test(null, null, null);
    }

    @Test
    public void returnsFalseIfNoDelegatesMatch() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(null, null, null)).thenReturn(false);
        val sut = AnyOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null, null));
        verify(delegate, times(3)).test(null, null, null);
    }

    @Test
    public void stripsConstantFalse() {
        val delegate = mock(TernaryPredicate.class);
        val sut = AnyOf.create(Arrays.asList(delegate, ConstantFalse.create(), delegate));
        assertThat(sut, instanceOf(AnyOf.class));
        assertThat(((AnyOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortCircuitsOnConstantTrue() {
        val sut = AnyOf.create(Arrays.asList((a, b, c) -> true, ConstantTrue.create(), (a, b, c) -> true));
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void unwrapsNestedAnyOf() {
        val delegate = mock(TernaryPredicate.class);
        val nested = AnyOf.create(Arrays.asList(delegate, delegate));
        val sut = AnyOf.create(Arrays.asList(delegate, nested));
        assertThat(sut, instanceOf(AnyOf.class));
        assertThat(((AnyOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }
}
