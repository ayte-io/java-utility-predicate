package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GreaterThan<T> implements AugmentedUnaryPredicate<T> {
    private final T reference;

    @ToString.Exclude
    private final Comparator<T> comparator;

    @Override
    public boolean test(T subject) {
        return comparator.compare(subject, reference) > 0;
    }

    public static <T> GreaterThan<T> create(T reference, @NonNull Comparator<T> comparator) {
        return new GreaterThan<>(reference, comparator);
    }

    public static <T extends Comparable<T>> GreaterThan<T> create(@NonNull T reference) {
        return create(reference, Comparator.naturalOrder());
    }
}
