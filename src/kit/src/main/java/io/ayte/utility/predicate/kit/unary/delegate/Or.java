package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Or<T> implements AugmentedUnaryPredicate<T> {
    private final Predicate<? super T> first;
    private final Predicate<? super T> second;

    @Override
    public boolean test(T subject) {
        return first.test(subject) || second.test(subject);
    }

    public static <T> Or<T> create(@NonNull Predicate<? super T> first, @NonNull Predicate<? super T> second) {
        return new Or<>(first, second);
    }
}
