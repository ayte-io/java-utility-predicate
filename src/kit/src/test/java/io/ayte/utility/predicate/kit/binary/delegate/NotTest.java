package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.binary.standard.UsingFirst;
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
        assertFalse(Not.create((a, b) -> true).test(null, null));
        assertTrue(Not.create((a, b) -> false).test(null, null));
    }

    @Test
    public void unwrapsNestedNot() {
        val delegate = UsingFirst.create();
        val sut = Not.create(Not.create(delegate));
        assertThat(sut, is(delegate));
    }

    @Test
    public void recognizesConstantTrue() {
        val sut = Not.create(ConstantTrue.create());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void recognizesConstantFalse() {
        val sut = Not.create(ConstantFalse.create());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }
}
