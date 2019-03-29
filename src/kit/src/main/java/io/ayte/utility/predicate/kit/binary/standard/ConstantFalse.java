package io.ayte.utility.predicate.kit.binary.standard;

import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.function.BiPredicate;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantFalse<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private static final ConstantFalse INSTANCE = new ConstantFalse<>();

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return false;
    }

    @Override
    public String toString() {
        return "ConstantFalse";
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> ConstantFalse<T1, T2> create() {
        return INSTANCE;
    }

    public static <T1, T2> boolean instanceOf(BiPredicate<T1, T2> predicate) {
        return predicate instanceof ConstantFalse;
    }
}
