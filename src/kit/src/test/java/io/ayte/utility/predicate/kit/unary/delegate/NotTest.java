package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.standard.Identity;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotTest {
    @Test
    public void satisfiesContract() {
        assertTrue(Not.create(any -> false).test(null));
        assertFalse(Not.create(any -> true).test(null));
    }

    @Test
    public void unwrapsNestedNot() {
        val delegate = Identity.create();
        val sut = Not.create(Not.create(delegate));
        assertThat(sut, is(delegate));
    }
}
