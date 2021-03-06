package io.ayte.utility.predicate.kit.binary.sortable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InAscendingOrderTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> InAscendingOrder.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, 1, true},
                {1, 2, true},
                {2, 1, false}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(int left, int right, boolean expectation) {
        assertThat(InAscendingOrder.<Integer>create().test(left, right), equalTo(expectation));
    }
}
