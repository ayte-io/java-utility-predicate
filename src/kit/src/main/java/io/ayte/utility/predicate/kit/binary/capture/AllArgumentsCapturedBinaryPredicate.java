package io.ayte.utility.predicate.kit.binary.capture;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllArgumentsCapturedBinaryPredicate<T1, T2> implements BooleanSupplier {
    private final BiPredicate<T1, T2> delegate;
    private final T1 alpha;
    private final T2 beta;

    @Override
    public boolean getAsBoolean() {
        return delegate.test(alpha, beta);
    }

    public static <T1, T2> BooleanSupplier create(@NonNull BiPredicate<T1, T2> delegate, T1 alpha, T2 beta) {
        return new AllArgumentsCapturedBinaryPredicate<>(delegate, alpha, beta);
    }
}
