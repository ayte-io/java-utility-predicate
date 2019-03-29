package io.ayte.utility.predicate.kit.binary.delegate.collection;

import io.ayte.utility.predicate.kit.binary.delegate.collection.AnyOf;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.UsingFirst;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiPredicate;

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
    public void returnsTrueIfAnyPredicateReturnsTrue() {
        val mock = mock(BiPredicate.class);
        when(mock.test(null, null)).thenReturn(true, false);
        val sut = AnyOf.create(Arrays.asList(mock, mock));
        assertTrue(sut.test(null, null));
        verify(mock, times(1)).test(null, null);
    }

    @Test
    public void returnsFalseIfAllPredicatesReturnFalse() {
        val mock = mock(BiPredicate.class);
        when(mock.test(null, null)).thenReturn(false);
        val sut = AnyOf.create(Arrays.asList(mock, mock));
        assertFalse(sut.test(null, null));
        verify(mock, times(2)).test(null, null);
    }

    @Test
    public void stripsConstantFalse() {
        BiPredicate<Object, Object> delegate = (a, b) -> true;
        val sut = AnyOf.create(Arrays.asList(delegate, delegate, ConstantFalse.create()));
        assertThat(sut, instanceOf(AnyOf.class));
        assertThat(((AnyOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void createsConstantFalseOnEmptyDelegates() {
        val sut = AnyOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void passesSingleDelegateThrough() {
        val delegate = UsingFirst.create();
        val sut = AnyOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }
}
