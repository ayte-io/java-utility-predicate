package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Objects;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NoneElementsEqual<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    private final Object reference;

    @Override
    public boolean test(Iterable<E> subject) {
        for (val element : subject) {
            if (Objects.equals(reference, element)) {
                return false;
            }
        }
        return true;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(Object reference) {
        return new NoneElementsEqual<>(reference);
    }
}
