package io.ayte.utility.predicate.kit.binary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XorTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> Xor.create(null, (a, b) -> true));
        assertThrows(NullPointerException.class, () -> Xor.create((a, b) -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertFalse(Xor.create((a, b) -> true, (a, b) -> true).test(null, null));
        assertTrue(Xor.create((a, b) -> true, (a, b) -> false).test(null, null));
        assertTrue(Xor.create((a, b) -> false, (a, b) -> true).test(null, null));
        assertFalse(Xor.create((a, b) -> false, (a, b) -> false).test(null, null));
    }
}
