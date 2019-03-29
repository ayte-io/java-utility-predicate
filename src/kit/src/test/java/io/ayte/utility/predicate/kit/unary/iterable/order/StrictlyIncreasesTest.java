package io.ayte.utility.predicate.kit.unary.iterable.order;

import io.ayte.utility.predicate.kit.unary.iterable.order.StrictlyIncreases;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class StrictlyIncreasesTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> StrictlyIncreases.create(null));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(NullPointerException.class, () -> StrictlyIncreases.create().test(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {Arrays.asList(1, 2, 3), true},
                {Arrays.asList(1, 2, 2), false},
                {Arrays.asList(1, 2, 1), false},
                {Collections.singletonList(1), true},
                {Collections.emptyList(), true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(Collection<Integer> subject, boolean expectation) {
        val sut = StrictlyIncreases.<Integer>create();
        assertThat(sut.test(subject), equalTo(expectation));
    }

}
