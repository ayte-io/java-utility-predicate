package io.ayte.utility.predicate.kit.binary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UsingSecondTest {
    @Test
    public void returnsArgument() {
        assertTrue(UsingSecond.create().test(null, true));
        assertFalse(UsingSecond.create().test(null, false));
        assertFalse(UsingSecond.create().test(null, null));
    }

    @Test
    public void describesItself() {
        assertThat(UsingSecond.create().toString(), equalTo("UsingSecond"));
    }
}
