package io.ayte.utility.predicate.kit.unary.standard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ConstantTrueTest {
    @Test
    public void fulfillsContract() {
        assertThat(ConstantTrue.create().test(null), is(true));
    }

    @Test
    public void describesItself() {
        assertThat(ConstantTrue.create().toString(), equalTo("ConstantTrue"));
    }
}
