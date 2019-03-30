package io.ayte.utility.predicate.kit.ternary.capture;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GammaCapturedTernaryPredicate<T1, T2, T3> implements AugmentedBinaryPredicate<T1, T2> {
    private final TernaryPredicate<T1, T2, T3> delegate;
    private final T3 gamma;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return delegate.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> BinaryPredicate<T1, T2> create(
            @NonNull TernaryPredicate<T1, T2, T3> delegate,
            T3 gamma
    ) {
        return new GammaCapturedTernaryPredicate<>(delegate, gamma);
    }
}
