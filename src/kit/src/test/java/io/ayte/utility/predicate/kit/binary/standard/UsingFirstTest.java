package io.ayte.utility.predicate.kit.binary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UsingFirstTest {
    @Test
    public void returnsArgument() {
        assertTrue(UsingFirst.create().test(true, null));
        assertFalse(UsingFirst.create().test(false, null));
        assertFalse(UsingFirst.create().test(null, null));
    }

    @Test
    public void describesItself() {
        assertThat(UsingFirst.create().toString(), equalTo("UsingFirst"));
    }
}
