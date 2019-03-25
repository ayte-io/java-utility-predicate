package io.ayte.utility.predicate.kit.unary.value;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class WithinTest {
    public static Object[][] variantProvider() {
        return new Object[][] {
                {Within.create(1, true, 3, true), 1, false},
                {Within.create(1, true, 3, true), 2, true},
                {Within.create(1, true, 3, true), 3, false},

                {Within.create(1, false, 3, true), 1, true},
                {Within.create(1, false, 3, true), 2, true},
                {Within.create(1, false, 3, true), 3, false},

                {Within.create(1, true, 3, false), 1, false},
                {Within.create(1, true, 3, false), 2, true},
                {Within.create(1, true, 3, false), 3, true},

                {Within.create(1, false, 3, false), 1, true},
                {Within.create(1, false, 3, false), 2, true},
                {Within.create(1, false, 3, false), 3, true},
        };
    }

    @ParameterizedTest
    @MethodSource("variantProvider")
    public void satisfiesContract(Within<Integer> predicate, int candidate, boolean expectation) {
        assertThat(predicate.test(candidate), equalTo(expectation));
    }

    public static Object[][] descriptionProvider() {
        return new Object[][] {
                {Within.create(1, false, 2, false), "Within([1, 2])"},
                {Within.create(1, false, 2, true), "Within([1, 2))"},
                {Within.create(1, true, 2, false), "Within((1, 2])"},
                {Within.create(1, true, 2, true), "Within((1, 2))"},
        };
    }

    @ParameterizedTest
    @MethodSource("descriptionProvider")
    public void describesItself(Within<Integer> predicate, String expectation) {
        assertThat(predicate.toString(), equalTo(expectation));
    }
}
