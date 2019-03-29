package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.delegate.OneOf;
import io.ayte.utility.predicate.kit.binary.delegate.Or;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.binary.delegate.AllOf;
import io.ayte.utility.predicate.kit.binary.delegate.And;
import io.ayte.utility.predicate.kit.binary.delegate.AnyOf;
import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.delegate.NoneOf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.BiPredicate;

// CLOVER:OFF

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryPredicates {
    public static <T, U> BinaryPredicate<T, U> constant(boolean value) {
        return value ? anyTrue() : anyFalse();
    }

    public static <T, U> BinaryPredicate<T, U> anyTrue() {
        return ConstantTrue.create();
    }

    public static <T, U> BinaryPredicate<T, U> anyFalse() {
        return ConstantFalse.create();
    }

    public static <T, U> BinaryPredicate<T, U> not(@NonNull BiPredicate<T, U> predicate) {
        return Not.create(predicate);
    }

    public static <T, U> BinaryPredicate<T, U> allOf(@NonNull Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return AllOf.create(predicates);
    }

    public static <T, U> BinaryPredicate<T, U> and(
            @NonNull BiPredicate<? super T, ? super U> first,
            @NonNull BiPredicate<? super T, ? super U> second
    ) {
        return And.create(first, second);
    }

    public static <T, U> BinaryPredicate<T, U> anyOf(@NonNull Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return AnyOf.create(predicates);
    }

    public static <T, U> BinaryPredicate<T, U> or(
            @NonNull BiPredicate<? super T, ? super U> first,
            @NonNull BiPredicate<? super T, ? super U> second
    ) {
        return Or.create(first, second);
    }

    public static <T, U> BinaryPredicate<T, U> noneOf(@NonNull Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return NoneOf.create(predicates);
    }

    public static <T, U> BinaryPredicate<T, U> oneOf(@NonNull Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return OneOf.create(predicates);
    }
}
