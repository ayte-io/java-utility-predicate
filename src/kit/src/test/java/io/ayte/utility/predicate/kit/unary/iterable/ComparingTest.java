package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.kit.binary.ConstantFalse;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ComparingTest {
    @Test
    @SuppressWarnings("unchecked")
    public void iteratesUntilFalseResponse() {
        val mock = mock(BiPredicate.class);
        when(mock.test(any(), any())).thenReturn(true);
        when(mock.test(3, 4)).thenReturn(false);
        val sut = Comparing.create(mock);
        assertFalse(sut.test(Arrays.asList(1, 2, 3, 4, 5)));
        verify(mock, times(1)).test(1, 2);
        verify(mock, times(1)).test(2, 3);
        verify(mock, times(1)).test(3, 4);
        verify(mock, never()).test(4, 5);
    }

    @Test
    public void returnsTrueOnSingleElementCollection() {
        val sut = Comparing.create(ConstantFalse.create());
        assertTrue(sut.test(Collections.singleton(1)));
    }

    @Test
    public void returnsTrueOnEmptyCollection() {
        val sut = Comparing.create(ConstantFalse.create());
        assertTrue(sut.test(Collections.emptySet()));
    }

    @Test
    public void rejectsNull() {
        assertThrows(NullPointerException.class, () -> Comparing.create(null));
    }
}
