package io.ayte.utility.predicate.kit.binary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AndTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> And.create(null, (a, b) -> true));
        assertThrows(NullPointerException.class, () -> And.create((a, b) -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertTrue(And.create((a, b) -> true, (a, b) -> true).test(null, null));
        assertFalse(And.create((a, b) -> true, (a, b) -> false).test(null, null));
        assertFalse(And.create((a, b) -> false, (a, b) -> true).test(null, null));
        assertFalse(And.create((a, b) -> false, (a, b) -> false).test(null, null));
    }
}
