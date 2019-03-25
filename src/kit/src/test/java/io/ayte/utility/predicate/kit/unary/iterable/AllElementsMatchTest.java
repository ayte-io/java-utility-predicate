package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllElementsMatchTest {
    @Test
    public void alwaysReturnsTrueOnEmptySubject() {
        val sut = AllElementsMatch.create(ConstantFalse.create());
        assertThat(sut.test(Collections.emptyList()), equalTo(true));
    }

    @Test
    public void returnsFalseIfAnyElementFails() {
        val sut = AllElementsMatch.create(ConstantFalse.create());
        assertThat(sut.test(Collections.singleton(null)), equalTo(false));
    }

    @Test
    public void returnsTrueIfAllElementSucceed() {
        val sut = AllElementsMatch.create(ConstantTrue.create());
        assertThat(sut.test(Collections.singleton(null)), equalTo(true));
    }

    @Test
    public void doesNotAcceptNull() {
        assertThrows(NullPointerException.class, () -> AllElementsMatch.create(null));
    }
}
