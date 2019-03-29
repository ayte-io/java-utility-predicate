package io.ayte.utility.predicate.kit.ternary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ConstantFalseTest {
    @Test
    public void satisfiesContract() {
        assertFalse(ConstantFalse.create().test(null, null, null));
    }

    @Test
    public void describesItself() {
        assertThat(ConstantFalse.create().toString(), equalTo("ConstantFalse"));
    }
}
