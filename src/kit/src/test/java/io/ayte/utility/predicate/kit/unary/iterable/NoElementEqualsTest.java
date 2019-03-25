package io.ayte.utility.predicate.kit.unary.iterable;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoElementEqualsTest {
    @Test
    public void rejectsNullSubject() {
        assertThrows(NullPointerException.class, () -> NoElementEquals.create(null).test(null));
    }

    @Test
    public void acceptsNullReference() {
        assertDoesNotThrow(() -> NoElementEquals.create(null));
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, Collections.emptyList(), true},
                {1, Arrays.asList(2, 3), true},
                {1, Arrays.asList(1, 2, 3), false},
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(Integer reference, List<Integer> subject, boolean expectation) {
        val sut = NoElementEquals.<Integer>create(reference);
        assertThat(sut.test(subject), equalTo(expectation));
    }
}
