package io.ayte.utility.predicate.kit.unary.iterable.aggregate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
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
public class AnyElementMatches<E> implements AugmentedUnaryPredicate<Iterable<E>> {
    @NonNull
    private final Predicate<? super E> predicate;

    @Override
    public boolean test(@NonNull Iterable<E> subject) {
        for (val element : subject) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <E> UnaryPredicate<Iterable<E>> create(@NonNull Predicate<? super E> predicate) {
        if (predicate instanceof ConstantTrue) {
            return ConstantTrue.create();
        }
        if (predicate instanceof ConstantFalse) {
            return ConstantFalse.create();
        }
        return new AnyElementMatches<>(predicate);
    }
}
