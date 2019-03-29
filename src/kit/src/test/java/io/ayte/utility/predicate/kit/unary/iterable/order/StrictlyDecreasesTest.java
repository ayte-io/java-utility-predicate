package io.ayte.utility.predicate.kit.unary.iterable.order;

import io.ayte.utility.predicate.kit.unary.iterable.order.StrictlyDecreases;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrictlyDecreasesTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> StrictlyDecreases.create(null));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(NullPointerException.class, () -> StrictlyDecreases.create().test(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {Arrays.asList(3, 2, 1), true},
                {Arrays.asList(3, 2, 2), false},
                {Arrays.asList(3, 2, 3), false},
                {Collections.singletonList(3), true},
                {Collections.emptyList(), true}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(Collection<Integer> subject, boolean expectation) {
        val sut = StrictlyDecreases.<Integer>create();
        assertThat(sut.test(subject), equalTo(expectation));
    }
}
