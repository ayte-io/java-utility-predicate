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
public class AlphaCapturedBinaryPredicate<T1, T2> implements AugmentedUnaryPredicate<T2> {
    private final BiPredicate<T1, T2> delegate;
    private final T1 alpha;

    @Override
    public boolean test(T2 beta) {
        return delegate.test(alpha, beta);
    }

    public static <T1, T2> UnaryPredicate<T2> create(@NonNull BiPredicate<T1, T2> delegate, T1 alpha) {
        return new AlphaCapturedBinaryPredicate<>(delegate, alpha);
    }
}
