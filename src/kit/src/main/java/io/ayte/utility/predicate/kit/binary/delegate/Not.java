package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiPredicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Not<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    @Getter
    private final BiPredicate<T1, T2> delegate;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return !delegate.test(alpha, beta);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> create(@NonNull BiPredicate<T1, T2> delegate) {
        if (delegate instanceof ConstantTrue) {
            return ConstantFalse.create();
        }
        if (delegate instanceof ConstantFalse) {
            return ConstantTrue.create();
        }
        if (delegate instanceof Not) {
            return Wrapper.create(((Not<T1, T2>) delegate).getDelegate());
        }
        return new Not<>(delegate);
    }
}
