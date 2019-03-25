package io.ayte.utility.predicate.kit.unary.value;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotEqualToTest {
    @Test
    public void acceptsNull() {
        assertDoesNotThrow(() -> NotEqualTo.create(null));
    }

    @Test
    public void satisfiesContract() {
        val sut = NotEqualTo.create(1);
        assertTrue(sut.test(2));
        assertFalse(sut.test(1));
    }
}
