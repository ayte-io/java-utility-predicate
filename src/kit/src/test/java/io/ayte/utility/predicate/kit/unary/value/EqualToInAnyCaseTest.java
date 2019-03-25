package io.ayte.utility.predicate.kit.unary.value;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EqualToInAnyCaseTest {
    @Test
    public void doesNotAcceptNull() {
        assertThrows(NullPointerException.class, () -> EqualToInAnyCase.create(null));
    }

    @Test
    public void fulfillsContract() {
        val sut = EqualToInAnyCase.create("reference");
        assertThat("Equality check failed", sut.test("reference"));
        assertThat("Equality check failed", sut.test("REFERENCE"));
        assertThat("Non-equality check failed", !sut.test("covfefe"));
    }
}
