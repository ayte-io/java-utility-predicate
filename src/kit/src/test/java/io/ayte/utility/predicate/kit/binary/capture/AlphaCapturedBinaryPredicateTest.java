package io.ayte.utility.predicate.kit.binary.capture;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlphaCapturedBinaryPredicateTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AlphaCapturedBinaryPredicate.create(null, 1));
    }

    @Test
    public void acceptsNullArguments() {
        assertDoesNotThrow(() -> AlphaCapturedBinaryPredicate.create((a, b) -> true, null).test(null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(1, 2)).thenReturn(true);
        val sut = AlphaCapturedBinaryPredicate.create(delegate, 1);
        assertTrue(sut.test(2));
        verify(delegate, times(1)).test(1, 2);
    }
}
