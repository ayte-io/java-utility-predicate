package io.ayte.utility.predicate.kit.unary.map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValueOfTest {
    @Test
    public void throwsOnNull() {
        assertThrows(NullPointerException.class, () -> ValueOf.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {Collections.emptyMap(), 1, false},
                {Collections.singletonMap(1, 2), 1, false},
                {Collections.singletonMap(1, 1), 1, true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void fulfillsContract(Map<Integer, Integer> map, int value, boolean expectation) {
        assertThat(ValueOf.create(map).test(value), equalTo(expectation));
    }
}
