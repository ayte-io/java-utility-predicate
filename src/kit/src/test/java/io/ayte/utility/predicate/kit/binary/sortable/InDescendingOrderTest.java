package io.ayte.utility.predicate.kit.binary.sortable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class InDescendingOrderTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> InDescendingOrder.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, 1, true},
                {1, 2, false},
                {2, 1, true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(int left, int right, boolean expectation) {
        assertThat(InDescendingOrder.<Integer>create().test(left, right), equalTo(expectation));
    }
}
