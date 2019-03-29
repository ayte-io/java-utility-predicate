package io.ayte.utility.predicate.kit.binary.sortable;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class InStrictDescendingOrder<T> implements AugmentedBinaryPredicate<T, T> {
    private final Comparator<T> comparator;

    @Override
    public boolean test(T alpha, T beta) {
        return comparator.compare(alpha, beta) > 0 ;
    }

    public static <T> BinaryPredicate<T, T> create(@NonNull Comparator<T> comparator) {
        return new InStrictDescendingOrder<>(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> create() {
        return InStrictDescendingOrder.<T>create(Comparator.naturalOrder());
    }
}
