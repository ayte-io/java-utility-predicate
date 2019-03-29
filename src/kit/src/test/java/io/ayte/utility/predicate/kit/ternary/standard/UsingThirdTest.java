package io.ayte.utility.predicate.kit.ternary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsingThirdTest {
    @Test
    public void satisfiesContract() {
        assertTrue(UsingThird.create().test(null, null, true));
        assertFalse(UsingThird.create().test(null, null, false));
        assertFalse(UsingThird.create().test(null, null, null));
    }

    @Test
    public void describesItself() {
        assertThat(UsingThird.create().toString(), equalTo("UsingThird"));
    }
}
