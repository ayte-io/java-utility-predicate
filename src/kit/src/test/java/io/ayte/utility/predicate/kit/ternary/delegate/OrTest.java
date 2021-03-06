package io.ayte.utility.predicate.kit.ternary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> Or.create(null, (a, b, c) -> true));
        assertThrows(NullPointerException.class, () -> Or.create((a, b, c) -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertTrue(Or.create((a, b, c) -> true, (a, b, c) -> true).test(null, null, null));
        assertTrue(Or.create((a, b, c) -> true, (a, b, c) -> false).test(null, null, null));
        assertTrue(Or.create((a, b, c) -> false, (a, b, c) -> true).test(null, null, null));
        assertFalse(Or.create((a, b, c) -> false, (a, b, c) -> false).test(null, null, null));
    }
}
