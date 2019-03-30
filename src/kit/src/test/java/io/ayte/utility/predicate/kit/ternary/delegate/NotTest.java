package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.ternary.standard.UsingFirst;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> Not.create(null));
    }

    @Test
    public void satisfiesContract() {
        assertFalse(Not.create((a, b, c) -> true).test(null, null, null));
        assertTrue(Not.create((a, b, c) -> false).test(null, null, null));
    }

    @Test
    public void returnsConstantFalseOnConstantTrue() {
        assertThat(Not.create(ConstantTrue.create()), instanceOf(ConstantFalse.class));
    }

    @Test
    public void returnsConstantTrueOnConstantFalse() {
        assertThat(Not.create(ConstantFalse.create()), instanceOf(ConstantTrue.class));
    }

    @Test
    public void unwrapsNestedDelegate() {
        val delegate = UsingFirst.create();
        val subject = Not.create(delegate);
        assertThat(Not.create(subject), is(delegate));
    }
}
