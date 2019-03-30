package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoneElementsMatchTest {
    @Test
    public void rejectsNullPredicate() {
        assertThrows(NullPointerException.class, () -> NoneElementsMatch.create(null));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(
                NullPointerException.class,
                () -> NoneElementsMatch.create(any -> false).test(null)
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void rejectsIfSingleElementCausesFalse() {
        val mock = mock(Predicate.class);
        when(mock.test(any())).thenReturn(false, false, true);
        val sut = NoneElementsMatch.create(mock);
        assertFalse(sut.test(Arrays.asList(1, 2, 3, 4)));
        verify(mock, times(1)).test(1);
        verify(mock, times(1)).test(2);
        verify(mock, times(1)).test(3);
        verify(mock, never()).test(4);
    }

    @Test
    public void returnsTrueOnEmptySubject() {
        val sut = NoneElementsMatch.create(any -> false);
        assertTrue(sut.test(Collections.emptySet()));
    }

    @Test
    public void shortcutsOnConstantTrue() {
        val sut = NoneElementsMatch.create(ConstantTrue.create());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void shortcutsOnConstantFalse() {
        val sut = NoneElementsMatch.create(ConstantFalse.create());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }
}
