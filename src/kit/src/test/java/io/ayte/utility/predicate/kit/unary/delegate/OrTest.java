package io.ayte.utility.predicate.kit.unary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrTest {
    @Test
    public void satisfiesContract() {
        assertTrue(Or.create(any -> true, any -> true).test(null));
        assertTrue(Or.create(any -> false, any -> true).test(null));
        assertTrue(Or.create(any -> true, any -> false).test(null));
        assertFalse(Or.create(any -> false, any -> false).test(null));
    }

    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> Or.create(null, any -> true));
        assertThrows(NullPointerException.class, () -> Or.create(any -> true, null));
    }
}
