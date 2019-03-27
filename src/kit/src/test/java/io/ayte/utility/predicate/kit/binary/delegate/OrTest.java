package io.ayte.utility.predicate.kit.binary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> Or.create(null, (a, b) -> true));
        assertThrows(NullPointerException.class, () -> Or.create((a, b) -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertTrue(Or.create((a, b) -> true, (a, b) -> true).test(null, null));
        assertTrue(Or.create((a, b) -> true, (a, b) -> false).test(null, null));
        assertTrue(Or.create((a, b) -> false, (a, b) -> true).test(null, null));
        assertFalse(Or.create((a, b) -> false, (a, b) -> false).test(null, null));
    }
}
