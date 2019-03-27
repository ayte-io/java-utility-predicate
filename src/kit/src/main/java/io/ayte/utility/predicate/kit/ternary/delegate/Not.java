package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Not<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    @Getter(AccessLevel.PRIVATE)
    private final TernaryPredicate<T1, T2, T3> delegate;

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        return !delegate.test(alpha, beta, gamma);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(@NonNull TernaryPredicate<T1, T2, T3> predicate) {
        if (predicate instanceof ConstantTrue) {
            return ConstantFalse.create();
        }
        if (predicate instanceof ConstantFalse) {
            return ConstantTrue.create();
        }
        if (predicate instanceof Not) {
            return ((Not<T1, T2, T3>) predicate).getDelegate();
        }
        return new Not<>(predicate);
    }
}
