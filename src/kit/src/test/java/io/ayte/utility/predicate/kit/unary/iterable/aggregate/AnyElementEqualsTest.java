package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.kit.unary.iterable.aggregate.AnyElementEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AnyElementEqualsTest {
    @Test
    public void returnsFalseIfNoElementEquals() {
        assertFalse(AnyElementEquals.create(1).test(Collections.singleton(2)));
    }

    @Test
    public void returnsTrueIfSingleElementEquals() {
        assertTrue(AnyElementEquals.create(1).test(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void returnsFalseIfEmptyIterablePassed() {
        assertFalse(AnyElementEquals.create(null).test(Collections.emptyList()));
    }

    @Test
    public void rejectsNullIterable() {
        assertThrows(NullPointerException.class, () -> AnyElementEquals.create(null).test(null));
    }
}
