package io.ayte.utility.predicate.kit.unary.iterable.order;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Comparator;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StrictlyIncreases<T> implements AugmentedUnaryPredicate<Iterable<T>> {
    private final Comparator<? super T> comparator;

    @Override
    public boolean test(Iterable<T> subject) {
        val iterator = subject.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T previous = iterator.next();
        while (iterator.hasNext()) {
            val current = iterator.next();
            if (comparator.compare(previous, current) >= 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static <T> UnaryPredicate<Iterable<T>> create(@NonNull Comparator<? super T> comparator) {
        return new StrictlyIncreases<>(comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<Iterable<T>> create() {
        return create(Comparator.naturalOrder());
    }
}
