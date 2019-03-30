package io.ayte.utility.predicate.kit.unary.delegate;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ComputingTest {
    @Test
    public void rejectsNullMapper() {
        assertThrows(NullPointerException.class, () -> Computing.create(null, any -> true));
    }

    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> Computing.create(x -> x, null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsMapperAndPredicate() {
        val inversion = mock(Function.class);
        when(inversion.apply(any())).then(input -> !((Boolean) input.getArguments()[0]));
        val predicate = mock(Predicate.class);
        when(predicate.test(any())).then(input -> input.getArguments()[0]);
        val sut = Computing.create(inversion, predicate);
        assertTrue(sut.test(false));
        verify(inversion, times(1)).apply(false);
        verify(predicate, times(1)).test(true);
    }
}
