package io.ayte.utility.predicate.kit.binary;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.BiPredicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantTrue<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private static final ConstantTrue INSTANCE = new ConstantTrue<>();

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> ConstantTrue<T1, T2> create() {
        return INSTANCE;
    }

    public static <T1, T2> boolean instanceOf(BiPredicate<T1, T2> predicate) {
        return predicate instanceof ConstantTrue;
    }

    public static <T1, T2> boolean notInstanceOf(BiPredicate<T1, T2> predicate) {
        return !instanceOf(predicate);
    }
}
