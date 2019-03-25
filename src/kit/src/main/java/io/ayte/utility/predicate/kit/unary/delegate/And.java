package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class And<T> implements AugmentedUnaryPredicate<T> {
    private final Predicate<? super T> first;
    private final Predicate<? super T> second;

    @Override
    public boolean test(T subject) {
        return first.test(subject) && second.test(subject);
    }

    public static <T> UnaryPredicate<T> create(
            @NonNull Predicate<? super T> first,
            @NonNull Predicate<? super T> second
    ) {
        if (first instanceof ConstantFalse || second instanceof ConstantFalse) {
            return ConstantFalse.create();
        }
        if (first instanceof ConstantTrue && second instanceof ConstantTrue) {
            return ConstantTrue.create();
        }
        return new And<>(first, second);
    }
}
