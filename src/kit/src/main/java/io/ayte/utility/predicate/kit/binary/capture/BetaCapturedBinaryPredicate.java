package io.ayte.utility.predicate.kit.binary.capture;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiPredicate;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BetaCapturedBinaryPredicate<T1, T2> implements AugmentedUnaryPredicate<T1> {
    private final BiPredicate<T1, T2> delegate;
    private final T2 beta;

    @Override
    public boolean test(T1 alpha) {
        return delegate.test(alpha, beta);
    }

    public static <T1, T2> UnaryPredicate<T1> create(@NonNull BiPredicate<T1, T2> delegate, T2 beta) {
        return new BetaCapturedBinaryPredicate<>(delegate, beta);
    }
}
