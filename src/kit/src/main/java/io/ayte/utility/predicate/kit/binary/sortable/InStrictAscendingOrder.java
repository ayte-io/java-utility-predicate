package io.ayte.utility.predicate.kit.binary.sortable;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class InStrictAscendingOrder<T> implements AugmentedBinaryPredicate<T, T>, BinaryPredicate<T, T> {
    private final Comparator<T> comparator;

    @Override
    public boolean test(T alpha, T beta) {
        return comparator.compare(alpha, beta) < 0 ;
    }

    public static <T> BinaryPredicate<T, T> create(@NonNull Comparator<T> comparator) {
        return new InStrictAscendingOrder<>(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> create() {
        return InStrictAscendingOrder.<T>create(Comparator.naturalOrder());
    }
}
