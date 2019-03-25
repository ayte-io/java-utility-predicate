package io.ayte.utility.predicate.kit.unary.delegate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XorTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> Xor.create(null, any -> true));
        assertThrows(NullPointerException.class, () -> Xor.create(any -> true, null));
    }

    @Test
    public void satisfiesContract() {
        assertFalse(Xor.create(any -> true, any -> true).test(null));
        assertTrue(Xor.create(any -> true, any -> false).test(null));
        assertTrue(Xor.create(any -> false, any -> true).test(null));
        assertFalse(Xor.create(any -> false, any -> false).test(null));
    }
}
