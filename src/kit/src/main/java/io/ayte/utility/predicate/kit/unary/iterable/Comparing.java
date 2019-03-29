package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.function.BiPredicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Comparing<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final BiPredicate<? super E, ? super E> predicate;

    @Override
    public boolean test(Iterable<E> subject) {
        return apply(subject, predicate);
    }

    public static <T> boolean apply(
            @NonNull Iterable<? extends T> subject,
            @NonNull BiPredicate<? super T, ? super T> predicate
    ) {
        val iterator = subject.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T previous = iterator.next();
        while (iterator.hasNext()) {
            val current = iterator.next();
            if (!predicate.test(previous, current)) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(@NonNull BiPredicate<? super E, ? super E> predicate) {
        return new Comparing<>(predicate);
    }
}
