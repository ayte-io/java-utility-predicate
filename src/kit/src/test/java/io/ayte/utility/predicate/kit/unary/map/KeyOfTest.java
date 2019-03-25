package io.ayte.utility.predicate.kit.unary.map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KeyOfTest {
    @Test
    public void throwsOnNull() {
        assertThrows(NullPointerException.class, () -> KeyOf.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {Collections.emptyMap(), 1, false},
                {Collections.singletonMap(2, false), 1, false},
                {Collections.singletonMap(1, false), 1, true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void fulfillsContract(Map<Integer, Boolean> map, int key, boolean expectation) {
        assertThat(KeyOf.create(map).test(key), equalTo(expectation));
    }
}
