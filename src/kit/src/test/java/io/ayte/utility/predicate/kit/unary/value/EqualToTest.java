package io.ayte.utility.predicate.kit.unary.value;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class EqualToTest {
    @Test
    public void acceptsNulls() {
        val sut = EqualTo.create(null);
        assertThat("Null equality check failed", sut.test(null));
    }

    @Test
    public void fulfillsContract(){
        val sut = EqualTo.create(1);
        assertThat("Equality check failed", sut.test(1));
        assertThat("Non-equality check failed", !sut.test(2));
    }
}
