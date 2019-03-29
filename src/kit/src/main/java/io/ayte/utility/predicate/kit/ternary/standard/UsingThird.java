package io.ayte.utility.predicate.kit.ternary.standard;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingThird<T1, T2> implements AugmentedTernaryPredicate<T1, T2, Boolean> {
    private static final UsingThird INSTANCE = new UsingThird<>();

    @Override
    public boolean test(T1 alpha, T2 beta, Boolean gamma) {
        return Boolean.TRUE.equals(gamma);
    }

    @Override
    public String toString() {
        return "UsingThird";
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> TernaryPredicate<T1, T2, Boolean> create() {
        return INSTANCE;
    }
}
