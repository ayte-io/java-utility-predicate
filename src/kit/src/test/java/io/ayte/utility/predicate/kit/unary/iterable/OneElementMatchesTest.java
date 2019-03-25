package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
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

class OneElementMatchesTest {
    @Test
    public void rejectsNullPredicate() {
        assertThrows(NullPointerException.class, () -> OneElementMatches.create(null));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(
                NullPointerException.class,
                () -> OneElementMatches.create(ConstantFalse.create()).test(null)
        );
    }

    @Test
    public void returnsFalseOnEmptySubject() {
        val sut = OneElementMatches.create(ConstantTrue.create());
        assertFalse(sut.test(Collections.emptySet()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void returnsTrueOnSingleMatch() {
        val mock = mock(Predicate.class);
        when(mock.test(any())).thenReturn(true, false, false);
        val sut = OneElementMatches.create(mock);
        assertTrue(sut.test(Arrays.asList(1, 2, 3)));
        verify(mock, times(1)).test(1);
        verify(mock, times(1)).test(2);
        verify(mock, times(1)).test(3);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void returnsFalseOnMultipleMatches() {
        val mock = mock(Predicate.class);
        when(mock.test(any())).thenReturn(true);
        val sut = OneElementMatches.create(mock);
        assertFalse(sut.test(Arrays.asList(1, 2, 3)));
        verify(mock, times(1)).test(1);
        verify(mock, times(1)).test(2);
        verify(mock, never()).test(3);
    }
}
