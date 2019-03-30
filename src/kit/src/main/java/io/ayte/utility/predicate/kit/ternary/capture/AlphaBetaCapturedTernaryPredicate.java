package io.ayte.utility.predicate.kit.ternary.capture;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AlphaBetaCapturedTernaryPredicate<T1, T2, T3> implements AugmentedUnaryPredicate<T3> {
    private final TernaryPredicate<T1, T2, T3> delegate;
    private final T1 alpha;
    private final T2 beta;

    @Override
    public boolean test(T3 gamma) {
        return delegate.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> UnaryPredicate<T3> create(
            @NonNull TernaryPredicate<T1, T2, T3> delegate,
            T1 alpha,
            T2 beta
    ) {
        return new AlphaBetaCapturedTernaryPredicate<>(delegate, alpha, beta);
    }
}
