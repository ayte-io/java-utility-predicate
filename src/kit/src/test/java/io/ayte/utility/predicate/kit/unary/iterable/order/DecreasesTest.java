package io.ayte.utility.predicate.kit.unary.iterable.order;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecreasesTest {
    public static Object[][] dataProvider() {
        return new Object[][] {
                {Arrays.asList(1, 2, 1), false},
                {Arrays.asList(2, 2, 1), true},
                {Arrays.asList(3, 2, 1), true},
                {Collections.singletonList(1), true},
                {Collections.emptyList(), true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(List<Integer> subject, boolean expectation) {
        val sut = Decreases.<Integer>create();
        assertThat(sut.test(subject), equalTo(expectation));
    }

    @Test
    public void rejectsNull() {
        assertThrows(NullPointerException.class, () -> Decreases.create().test(null));
    }
}
