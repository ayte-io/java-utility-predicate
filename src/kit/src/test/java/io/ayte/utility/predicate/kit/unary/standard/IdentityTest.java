package io.ayte.utility.predicate.kit.unary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentityTest {
    @Test
    public void satisfiesContract() {
        assertTrue(Identity.create().test(true));
        assertFalse(Identity.create().test(false));
    }

    @Test
    public void describesItself() {
        assertThat(Identity.create().toString(), equalTo("Identity"));
    }
}
