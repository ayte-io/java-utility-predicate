package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EqualTo<T> implements AugmentedUnaryPredicate<T> {
    private final Object reference;

    @Override
    public boolean test(T subject) {
        return Objects.equals(reference, subject);
    }

    public static <T> EqualTo<T> create(Object reference) {
        return new EqualTo<>(reference);
    }
}
