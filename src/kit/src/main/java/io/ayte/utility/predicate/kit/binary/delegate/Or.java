package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiPredicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Or<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private final BiPredicate<? super T1, ? super T2> first;
    private final BiPredicate<? super T1, ? super T2> second;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return first.test(alpha, beta) || second.test(alpha, beta);
    }

    public static <T1, T2> Or<T1, T2> create(
            @NonNull BiPredicate<? super T1, ? super T2> first,
            @NonNull BiPredicate<? super T1, ? super T2> second
    ) {
        return new Or<>(first, second);
    }
}
