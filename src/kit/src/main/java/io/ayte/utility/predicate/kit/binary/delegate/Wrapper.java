package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiPredicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class Wrapper<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private final BiPredicate<T1, T2> predicate;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        return predicate.test(alpha, beta);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> create(@NonNull BiPredicate<T1, T2> predicate) {
        return predicate instanceof BinaryPredicate ? (BinaryPredicate<T1, T2>) predicate : new Wrapper<>(predicate);
    }
}
