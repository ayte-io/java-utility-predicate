package io.ayte.utility.predicate.kit.binary.sortable;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class InStrictDescendingOrderTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> InStrictDescendingOrder.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, 1, false},
                {1, 2, false},
                {2, 1, true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void fulfillsContract(int alpha, int beta, boolean expectation) {
        val result = InStrictDescendingOrder.<Integer>create();
        assertThat(result.test(alpha, beta), equalTo(expectation));
    }
}
