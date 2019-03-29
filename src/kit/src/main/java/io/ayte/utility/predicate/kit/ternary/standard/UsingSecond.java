package io.ayte.utility.predicate.kit.ternary.standard;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingSecond<T1, T2> implements AugmentedTernaryPredicate<T1, Boolean, T2> {
    private static final UsingSecond INSTANCE = new UsingSecond<>();

    @Override
    public boolean test(T1 alpha, Boolean beta, T2 gamma) {
        return Boolean.TRUE.equals(beta);
    }

    @Override
    public String toString() {
        return "UsingSecond";
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> TernaryPredicate<T1, Boolean, T2> create() {
        return INSTANCE;
    }
}
