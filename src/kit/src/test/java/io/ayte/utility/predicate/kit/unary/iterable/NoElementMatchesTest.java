package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoElementMatchesTest {
    @Test
    public void rejectsNullPredicate() {
        assertThrows(NullPointerException.class, () -> NoElementMatches.create(null));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(
                NullPointerException.class,
                () -> NoElementMatches.create(ConstantFalse.create()).test(null)
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void rejectsIfSingleElementCausesFalse() {
        val mock = mock(Predicate.class);
        when(mock.test(any())).thenReturn(false, false, true);
        val sut = NoElementMatches.create(mock);
        assertFalse(sut.test(Arrays.asList(1, 2, 3, 4)));
        verify(mock, times(1)).test(1);
        verify(mock, times(1)).test(2);
        verify(mock, times(1)).test(3);
        verify(mock, never()).test(4);
    }

    @Test
    public void returnsTrueOnEmptySubject() {
        val sut = NoElementMatches.create(ConstantFalse.create());
        assertTrue(sut.test(Collections.emptySet()));
    }
}
