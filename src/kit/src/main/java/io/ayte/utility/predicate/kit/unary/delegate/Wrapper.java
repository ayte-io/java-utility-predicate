package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class Wrapper<T> implements AugmentedUnaryPredicate<T> {
    private final Predicate<T> delegate;

    @Override
    public boolean test(T subject) {
        return delegate.test(subject);
    }

    public static <T> UnaryPredicate<T> create(@NonNull Predicate<T> predicate) {
        return predicate instanceof UnaryPredicate ? ((UnaryPredicate<T>) predicate) : new Wrapper<>(predicate);
    }
}
