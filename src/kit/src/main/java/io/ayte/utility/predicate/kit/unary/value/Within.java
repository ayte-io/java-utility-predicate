package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Comparator;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Within<T> implements AugmentedUnaryPredicate<T> {
    private final T floor;
    private final T ceiling;
    private final boolean exclusiveFloor;
    private final boolean exclusiveCeiling;
    private final Comparator<T> comparator;

    @Override
    public boolean test(T subject) {
        return lowerThan(floor, subject, exclusiveFloor) && lowerThan(subject, ceiling, exclusiveCeiling);
    }

    private boolean lowerThan(T checked, T bound, boolean exclusive) {
        val decision = comparator.compare(checked, bound);
        return decision < 0 || (!exclusive && decision == 0);
    }

    @Override
    public String toString() {
        return "Within("
                + (exclusiveFloor ? '(' : '[')
                + floor
                + ", "
                + ceiling
                + (exclusiveCeiling ? ')' : ']')
                + ')';
    }

    public static <T> Within<T> create(
            T floor,
            boolean exclusiveFloor,
            T ceiling,
            boolean exclusiveCeiling,
            @NonNull Comparator<T> comparator
    ) {
        return new Within<>(floor, ceiling, exclusiveFloor, exclusiveCeiling, comparator);
    }

    public static <T extends Comparable<T>> Within<T> create(
            @NonNull T floor,
            boolean exclusiveFloor,
            @NonNull T ceiling,
            boolean exclusiveCeiling
    ) {
        return create(floor, exclusiveFloor, ceiling, exclusiveCeiling, Comparator.naturalOrder());
    }

    // CLOVER:OFF

    public static <T> Within<T> create(T floor, T ceiling, boolean exclusive, @NonNull Comparator<T> comparator) {
        return create(floor, exclusive, ceiling, exclusive, comparator);
    }

    public static <T extends Comparable<T>> Within<T> create(@NonNull T floor, @NonNull T ceiling) {
        return create(floor, false, ceiling, false);
    }

    public static <T> Within<T> create(T floor, T ceiling, @NonNull Comparator<T> comparator) {
        return create(floor, false, ceiling, false, comparator);
    }

    public static <T extends Comparable<T>> Within<T> create(@NonNull T floor, @NonNull T ceiling, boolean exclusive) {
        return create(floor, exclusive, ceiling, exclusive);
    }
}
