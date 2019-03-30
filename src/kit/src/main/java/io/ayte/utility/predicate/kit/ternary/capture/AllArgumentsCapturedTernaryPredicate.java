package io.ayte.utility.predicate.kit.ternary.capture;

import io.ayte.utility.predicate.TernaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BooleanSupplier;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllArgumentsCapturedTernaryPredicate<T1, T2, T3> implements BooleanSupplier {
    private final TernaryPredicate<T1, T2, T3> delegate;
    private final T1 alpha;
    private final T2 beta;
    private final T3 gamma;

    @Override
    public boolean getAsBoolean() {
        return delegate.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> BooleanSupplier create(
            @NonNull TernaryPredicate<T1, T2, T3> delegate,
            T1 alpha,
            T2 beta,
            T3 gamma
    ) {
        return new AllArgumentsCapturedTernaryPredicate<>(delegate, alpha, beta, gamma);
    }
}
