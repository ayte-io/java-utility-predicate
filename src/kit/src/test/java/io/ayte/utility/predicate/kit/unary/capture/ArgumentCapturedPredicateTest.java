package io.ayte.utility.predicate.kit.unary.capture;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArgumentCapturedPredicateTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> ArgumentCapturedPredicate.create(null, 1));
    }

    @Test
    public void acceptsNullValue() {
        assertDoesNotThrow(() -> ArgumentCapturedPredicate.create(any -> true, null).getAsBoolean());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val delegate = mock(Predicate.class);
        when(delegate.test(any())).thenReturn(true);
        val value = 1;
        val sut = ArgumentCapturedPredicate.create(delegate, value);
        assertTrue(sut.getAsBoolean());
        verify(delegate, times(1)).test(value);
    }
}
