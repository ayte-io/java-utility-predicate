package io.ayte.utility.predicate.kit.unary.value;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GreaterThanTest {
    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, 0, false},
                {1, 1, false},
                {1, 2, true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(int threshold, int value, boolean expectation) {
        val sut = GreaterThan.create(threshold);
        assertThat(sut.test(value), equalTo(expectation));
    }

    @Test
    public void throwsOnNull() {
        assertThrows(NullPointerException.class, () -> GreaterThan.create(null));
    }
}
