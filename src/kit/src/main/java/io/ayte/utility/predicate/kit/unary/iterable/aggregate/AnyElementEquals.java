package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Objects;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnyElementEquals<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Object reference;

    @Override
    public boolean test(@NonNull Iterable<E> subject) {
        for (val element : subject) {
            if (Objects.equals(element, reference)) {
                return true;
            }
        }
        return false;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(Object reference) {
        return new AnyElementEquals<>(reference);
    }
}
