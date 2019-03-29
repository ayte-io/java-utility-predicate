package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.function.Predicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NoElementMatches<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Predicate<? super E> predicate;

    @Override
    public boolean test(@NonNull Iterable<E> subject) {
        for (val item : subject) {
            if (predicate.test(item)) {
                return false;
            }
        }
        return true;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(@NonNull Predicate<? super E> predicate) {
        return new NoElementMatches<>(predicate);
    }
}
