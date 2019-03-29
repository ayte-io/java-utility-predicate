package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.kit.unary.iterable.aggregate.OneElementEquals;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OneElementEqualsTest {
    public static Object[][] dataProvider() {
        return new Object[][] {
                {1, Collections.emptyList(), false},
                {1, Collections.singletonList(1), true},
                {1, Arrays.asList(1, 1, 2, 3, 4), false},
                {1, Arrays.asList(1, 2, 3, 4), true},
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void satisfiesContract(int reference, Collection<Integer> subject, boolean expectation) {
        val sut = OneElementEquals.<Integer>create(reference);
        assertThat(sut.test(subject), equalTo(expectation));
    }

    @Test
    public void rejectsNullSubject() {
        assertThrows(NullPointerException.class, () -> OneElementEquals.create(null).test(null));
    }

    @Test
    public void acceptsNullReference() {
        assertDoesNotThrow(() -> OneElementEquals.create(null));
    }
}
