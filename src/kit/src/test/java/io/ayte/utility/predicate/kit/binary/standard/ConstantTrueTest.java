package io.ayte.utility.predicate.kit.binary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstantTrueTest {
    @Test
    public void satisfiesContract() {
        assertTrue(ConstantTrue.create().test(null, null));
    }

    @Test
    public void describesItself() {
        assertThat(ConstantTrue.create().toString(), equalTo("ConstantTrue"));
    }
}
