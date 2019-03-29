package io.ayte.utility.predicate.kit.ternary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsingFirstTest {
    @Test
    public void satisfiesContract() {
        assertTrue(UsingFirst.create().test(true, null, null));
        assertFalse(UsingFirst.create().test(false, null, null));
        assertFalse(UsingFirst.create().test(null, null, null));
    }

    @Test
    public void describesItself() {
        assertThat(UsingFirst.create().toString(), equalTo("UsingFirst"));
    }
}
