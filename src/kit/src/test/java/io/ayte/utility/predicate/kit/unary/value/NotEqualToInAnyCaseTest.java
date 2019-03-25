package io.ayte.utility.predicate.kit.unary.value;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotEqualToInAnyCaseTest {
    @Test
    public void doesNotAcceptNull() {
        assertThrows(NullPointerException.class, () -> NotEqualToInAnyCase.create(null));
    }

    @Test
    public void satisfiesContract() {
        val sut = NotEqualToInAnyCase.create("reference");
        assertFalse(sut.test("reference"));
        assertFalse(sut.test("REFERENCE"));
        assertTrue(sut.test("wharrgarbl"));
    }
}
