package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AndTest {
    @Test
    public void returnsConstantFalseIfAnyDelegateIsConstantFalse() {
        assertThat(And.create(ConstantFalse.create(), ConstantTrue.create()), instanceOf(ConstantFalse.class));
        assertThat(And.create(ConstantTrue.create(), ConstantFalse.create()), instanceOf(ConstantFalse.class));
    }

    @Test
    public void rejectsEmptyDelegates() {
        assertThrows(NullPointerException.class, () -> And.create(null, ConstantFalse.create()));
        assertThrows(NullPointerException.class, () -> And.create(ConstantFalse.create(), null));
    }

    @Test
    public void returnsConstantTrueIfAllDelegatesAreConstantTrue() {
        assertThat(And.create(ConstantTrue.create(), ConstantTrue.create()), instanceOf(ConstantTrue.class));
    }

    @Test
    public void returnsTrueIfBothDelegatesReturnTrue() {
        val sut = And.create(any -> true, any -> true);
        assertTrue(sut.test(null));
    }

    @Test
    public void returnsFalseIfAnyDelegateReturnsFalse() {
        assertFalse(And.create(any -> true, any -> false).test(null));
        assertFalse(And.create(any -> false, any -> true).test(null));
    }
}
