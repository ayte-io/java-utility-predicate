package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotEqualTo<T> implements AugmentedUnaryPredicate<T> {
    private final Object reference;

    @Override
    public boolean test(T subject) {
        return !Objects.equals(reference, subject);
    }

    public static <T> NotEqualTo<T> create(Object reference) {
        return new NotEqualTo<>(reference);
    }
}
