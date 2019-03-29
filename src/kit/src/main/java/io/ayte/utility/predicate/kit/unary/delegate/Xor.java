package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Xor<T> implements AugmentedUnaryPredicate<T> {
    @Getter
    private final Predicate<? super T> first;
    @Getter
    private final Predicate<? super T> second;

    @Override
    public boolean test(T subject) {
        return first.test(subject) ^ second.test(subject);
    }

    public static <T> Xor<T> create(@NonNull Predicate<? super T> first, @NonNull Predicate<? super T> second) {
        return new Xor<>(first, second);
    }
}
