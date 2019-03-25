package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LessThanOrEqualTo<T> implements AugmentedUnaryPredicate<T> {
    private final T reference;

    @ToString.Exclude
    private final Comparator<T> comparator;

    @Override
    public boolean test(T subject) {
        return comparator.compare(subject, reference) <= 0;
    }

    public static <T> LessThanOrEqualTo<T> create(@NonNull T reference, @NonNull Comparator<T> comparator) {
        return new LessThanOrEqualTo<>(reference, comparator);
    }

    public static <T extends Comparable<T>> LessThanOrEqualTo<T> create(@NonNull T reference) {
        return create(reference, Comparator.naturalOrder());
    }
}
