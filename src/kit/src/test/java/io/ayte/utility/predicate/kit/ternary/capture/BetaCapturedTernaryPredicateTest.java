package io.ayte.utility.predicate.kit.ternary.capture;

import io.ayte.utility.predicate.TernaryPredicate;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BetaCapturedTernaryPredicateTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> BetaCapturedTernaryPredicate.create(null, 2));
    }

    @Test
    public void acceptsNullArguments() {
        assertDoesNotThrow(() -> BetaCapturedTernaryPredicate.create((a, b, c) -> true, 2).test(1, 3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(1, 2, 3)).thenReturn(true);
        val sut = BetaCapturedTernaryPredicate.create(delegate, 2);
        assertTrue(sut.test(1, 3));
        verify(delegate, times(1)).test(1, 2, 3);
    }
}
