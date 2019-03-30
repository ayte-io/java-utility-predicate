package io.ayte.utility.predicate.kit.unary.capture;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentCapturedPredicate<T> implements BooleanSupplier {
    private final Predicate<T> delegate;
    private final T value;

    @Override
    public boolean getAsBoolean() {
        return delegate.test(value);
    }

    public static <T> BooleanSupplier create(@NonNull Predicate<T> delegate, T value) {
        return new ArgumentCapturedPredicate<>(delegate, value);
    }
}
