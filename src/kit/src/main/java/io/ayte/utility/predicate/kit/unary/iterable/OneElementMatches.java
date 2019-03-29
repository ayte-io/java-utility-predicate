package io.ayte.utility.predicate.kit.unary.iterable;

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
public class OneElementMatches<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Predicate<? super E> delegate;

    @SuppressWarnings("Duplicates")
    @Override
    public boolean test(Iterable<E> subject) {
        boolean observed = false;
        for (val element : subject) {
            if (!delegate.test(element)) {
                continue;
            }
            if (observed) {
                return false;
            }
            observed = true;
        }
        return observed;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(@NonNull Predicate<? super E> delegate) {
        return new OneElementMatches<>(delegate);
    }
}
