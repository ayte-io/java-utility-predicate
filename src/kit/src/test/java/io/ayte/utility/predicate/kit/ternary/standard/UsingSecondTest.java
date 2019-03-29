package io.ayte.utility.predicate.kit.ternary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsingSecondTest {
    @Test
    public void satisfiesContract() {
        assertTrue(UsingSecond.create().test(null, true, null));
        assertFalse(UsingSecond.create().test(null, false, null));
        assertFalse(UsingSecond.create().test(null, null, null));
    }

    @Test
    public void describesItself() {
        assertThat(UsingSecond.create().toString(), equalTo("UsingSecond"));
    }
}
