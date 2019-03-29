package io.ayte.utility.predicate.kit.ternary.standard;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingFirst<T1, T2> implements AugmentedTernaryPredicate<Boolean, T1, T2> {
    private static final UsingFirst INSTANCE = new UsingFirst<>();

    @Override
    public boolean test(Boolean alpha, T1 beta, T2 gamma) {
        return Boolean.TRUE.equals(alpha);
    }

    @Override
    public String toString() {
        return "UsingFirst";
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> TernaryPredicate<Boolean, T1, T2> create() {
        return INSTANCE;
    }
}
