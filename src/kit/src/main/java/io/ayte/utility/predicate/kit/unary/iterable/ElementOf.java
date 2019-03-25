package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ElementOf<E> implements AugmentedUnaryPredicate<E> {
    private final Collection<? super E> pool;

    @Override
    public boolean test(E subject) {
        return pool.contains(subject);
    }

    public static <E> UnaryPredicate<E> create(@NonNull Collection<? super E> pool) {
        return new ElementOf<>(pool);
    }
}
