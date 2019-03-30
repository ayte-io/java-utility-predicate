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

class AllArgumentsCapturedTernaryPredicateTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AllArgumentsCapturedTernaryPredicate.create(null, 1, 2, 3));
    }

    @Test
    public void acceptsNullArguments() {
        assertDoesNotThrow(() -> {
            AllArgumentsCapturedTernaryPredicate
                    .create((a, b, c) -> true, null, null, null)
                    .getAsBoolean();
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(1, 2, 3)).thenReturn(true);
        val sut = AllArgumentsCapturedTernaryPredicate.create(delegate, 1, 2, 3);
        assertTrue(sut.getAsBoolean());
        verify(delegate, times(1)).test(1, 2, 3);
    }
}
