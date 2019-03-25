package io.ayte.utility.predicate.kit.unary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ConstantFalseTest {
    @Test
    public void fulfillsContract() {
        assertThat(ConstantFalse.create().test(null), is(false));
    }

    @Test
    public void describesItself() {
        assertThat(ConstantFalse.create().toString(), equalTo("ConstantFalse"));
    }
}
