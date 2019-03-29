package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class NoneOfTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> NoneOf.create(null));
    }

    @Test
    public void returnsConstantTrueOnEmptyDelegates() {
        val sut = NoneOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void returnsInvertedSingleDelegate() {
        val delegate = mock(TernaryPredicate.class);
        val expectation = Not.create(delegate);
        assertThat(NoneOf.create(Collections.singleton(delegate)), equalTo(expectation));
    }

    @Test
    public void returnsFalseIfAnyDelegateReturnsTrue() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(null, null, null)).thenReturn(false, true, false);
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null, null));
        verify(delegate, times(2)).test(null, null, null);
    }

    @Test
    public void returnsTrueIfAllDelegatesReturnFalse() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(null, null, null)).thenReturn(false);
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertTrue(sut.test(null, null, null));
        verify(delegate, times(3)).test(null, null, null);
    }

    @Test
    public void ignoresConstantFalse() {
        val delegate = mock(TernaryPredicate.class);
        val sut = NoneOf.create(Arrays.asList(delegate, ConstantFalse.create(), delegate));
        assertThat(sut, instanceOf(NoneOf.class));
        assertThat(((NoneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortCircuitsOnConstantTrue() {
        val delegate = mock(TernaryPredicate.class);
        val sut = NoneOf.create(Arrays.asList(delegate, ConstantTrue.create(), delegate));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void unwrapsNestedNoneOf() {
        val delegate = mock(TernaryPredicate.class);
        val nested = NoneOf.create(Arrays.asList(delegate, delegate));
        val sut = NoneOf.create(Arrays.asList(delegate, nested));
        assertThat(sut, instanceOf(NoneOf.class));
        assertThat(((NoneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }
}
