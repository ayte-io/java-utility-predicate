package io.ayte.utility.predicate.kit.unary.iterable;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Objects;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneElementEquals<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Object reference;

    @Override
    public boolean test(Iterable<E> subject) {
        boolean observed = false;
        for (val element : subject) {
            if (!Objects.equals(reference, element)) {
                continue;
            }
            if (observed) {
                return false;
            }
            observed = true;
        }
        return observed;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(Object reference) {
        return new OneElementEquals<>(reference);
    }
}
