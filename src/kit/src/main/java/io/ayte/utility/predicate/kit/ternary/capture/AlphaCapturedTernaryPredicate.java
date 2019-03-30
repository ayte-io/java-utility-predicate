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
public class AlphaCapturedTernaryPredicate<T1, T2, T3> implements AugmentedBinaryPredicate<T2, T3> {
    private final TernaryPredicate<T1, T2, T3> delegate;
    private final T1 alpha;

    @Override
    public boolean test(T2 beta, T3 gamma) {
        return delegate.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> BinaryPredicate<T2, T3> create(
            @NonNull TernaryPredicate<T1, T2, T3> delegate,
            T1 alpha
    ) {
        return new AlphaCapturedTernaryPredicate<>(delegate, alpha);
    }
}
