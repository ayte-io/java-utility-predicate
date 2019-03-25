package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Objects;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllElementsEqual<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Object reference;

    @Override
    public boolean test(@NonNull Iterable<E> subject) {
        for (val entry : subject) {
            if (!Objects.equals(entry, reference)) {
                return false;
            }
        }
        return true;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(Object reference) {
        return new AllElementsEqual<>(reference);
    }
}