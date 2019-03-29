package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Or<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    private final TernaryPredicate<? super T1, ? super T2, ? super T3> first;
    private final TernaryPredicate<? super T1, ? super T2, ? super T3> second;

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        return first.test(alpha, beta, gamma) || second.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> first,
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> second
    ) {
        return new Or<>(first, second);
    }
}
