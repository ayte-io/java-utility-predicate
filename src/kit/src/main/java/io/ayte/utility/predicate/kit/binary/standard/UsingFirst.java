package io.ayte.utility.predicate.kit.binary.standard;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingFirst<T> implements AugmentedBinaryPredicate<Boolean, T> {
    private static final UsingFirst INSTANCE = new UsingFirst<>();

    @Override
    public boolean test(Boolean subject, T any) {
        return Boolean.TRUE.equals(subject);
    }

    @Override
    public String toString() {
        return "UsingFirst";
    }

    @SuppressWarnings("unchecked")
    public static <T> BinaryPredicate<Boolean, T> create() {
        return INSTANCE;
    }
}
