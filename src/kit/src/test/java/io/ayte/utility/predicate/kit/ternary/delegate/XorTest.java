package io.ayte.utility.predicate.kit.ternary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XorTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> Xor.create(null, (a, b, c) -> true));
        assertThrows(NullPointerException.class, () -> Xor.create((a, b, c) -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertFalse(Xor.create((a, b, c) -> true, (a, b, c) -> true).test(null, null, null));
        assertTrue(Xor.create((a, b, c) -> true, (a, b, c) -> false).test(null, null, null));
        assertTrue(Xor.create((a, b, c) -> false, (a, b, c) -> true).test(null, null, null));
        assertFalse(Xor.create((a, b, c) -> false, (a, b, c) -> false).test(null, null, null));
    }
}
