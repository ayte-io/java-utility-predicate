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
import static org.mockito.Mockito.when;

class AnyElementMatchesTest {
    @SuppressWarnings("unchecked")
    @Test
    public void returnsTrueIfAnyElementMatches() {
        val predicate = mock(Predicate.class);
        when(predicate.test(any())).thenReturn(false, false, true);
        val sut = AnyElementMatches.create(predicate);
        assertTrue(sut.test(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void returnsFalseIfNoElementMatches() {
        val sut = AnyElementMatches.create(ConstantFalse.create());
        assertFalse(sut.test(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void returnsFalseOnEmptyIterable() {
        val sut = AnyElementMatches.create(ConstantTrue.create());
        assertFalse(sut.test(Collections.emptyList()));
    }

    @Test
    public void rejectsNullIterable() {
        assertThrows(
                NullPointerException.class,
                () -> AnyElementMatches.create(ConstantTrue.create()).test(null)
        );
    }

    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AnyElementMatches.create(null));
    }
}
