package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiPredicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Not<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    @Getter(AccessLevel.PRIVATE)
    private final BiPredicate<T1, T2> delegate;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return !delegate.test(alpha, beta);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> create(BiPredicate<T1, T2> delegate) {
        if (delegate instanceof Not) {
            return Wrapper.create(((Not<T1, T2>) delegate).getDelegate());
        }
        return new Not<>(delegate);
    }
}
