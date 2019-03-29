package io.ayte.utility.predicate.kit.binary.standard;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingSecond<T> implements AugmentedBinaryPredicate<T, Boolean> {
    private static final UsingSecond INSTANCE = new UsingSecond<>();

    @Override
    public boolean test(T any, Boolean subject) {
        return Boolean.TRUE.equals(subject);
    }

    @Override
    public String toString() {
        return "UsingSecond";
    }

    @SuppressWarnings("unchecked")
    public static <T> BinaryPredicate<T, Boolean> create() {
        return INSTANCE;
    }
}
