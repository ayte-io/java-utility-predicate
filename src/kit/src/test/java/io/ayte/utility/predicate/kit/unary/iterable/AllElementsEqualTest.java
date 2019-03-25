package io.ayte.utility.predicate.kit.unary.iterable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class AllElementsEqualTest {
    public static Object[][] variantProvider() {
        return new Object[][] {
                {1, Arrays.asList(1, 1, 1), true},
                {1, Arrays.asList(1, 1, 2), false},
                {1, Collections.emptyList(), true}
        };
    }

    @ParameterizedTest
    @MethodSource("variantProvider")
    public void satisfiesContract(int reference, List<Integer> subject, boolean expectation) {
        assertThat(AllElementsEqual.<Integer>create(reference).test(subject), equalTo(expectation));
    }
}
