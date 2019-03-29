package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Predicate;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Derivative<T, S> implements AugmentedUnaryPredicate<T> {
    private final Function<T, S> mapper;
    private final Predicate<S> delegate;

    @Override
    public boolean test(T subject) {
        return delegate.test(mapper.apply(subject));
    }

    public static <T, S> Derivative<T, S> create(@NonNull Function<T, S> mapper, @NonNull Predicate<S> predicate) {
        return new Derivative<>(mapper, predicate);
    }
}
