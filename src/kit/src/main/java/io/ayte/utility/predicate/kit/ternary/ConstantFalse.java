package io.ayte.utility.predicate.kit.ternary;

import io.ayte.utility.predicate.TernaryPredicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantFalse<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    private static final ConstantFalse INSTANCE = new ConstantFalse<>();

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2, T3> ConstantFalse<T1, T2, T3> create() {
        return INSTANCE;
    }

    public static <T1, T2, T3> boolean instanceOf(TernaryPredicate<T1, T2, T3> predicate) {
        return predicate instanceof ConstantFalse;
    }

    public static <T1, T2, T3> boolean notInstanceOf(TernaryPredicate<T1, T2, T3> predicate) {
        return !instanceOf(predicate);
    }
}
