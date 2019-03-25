package io.ayte.utility.predicate.kit.unary.iterable;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElementOfTest {
    @Test
    public void rejectsNull() {
        assertThrows(NullPointerException.class, () -> ElementOf.create(null));
    }

    @Test
    public void satisfiesContract() {
        val sut = ElementOf.create(Collections.singleton(1));
        assertTrue(sut.test(1));
        assertFalse(sut.test(2));
    }
}
