package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.kit.binary.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.ConstantTrue;
import io.ayte.utility.predicate.kit.binary.delegate.AllOf;
import io.ayte.utility.predicate.kit.binary.delegate.And;
import io.ayte.utility.predicate.kit.binary.delegate.AnyOf;
import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.delegate.NoneOf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.BiPredicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryPredicates {
    public static <T, U> BiPredicate<T, U> constant(boolean value) {
        return value ? anyTrue() : anyFalse();
    }

    public static <T, U> BiPredicate<T, U> anyTrue() {
        return ConstantTrue.create();
    }

    public static <T, U> BiPredicate<T, U> anyFalse() {
        return ConstantFalse.create();
    }

    public static <T, U> BiPredicate<T, U> not(BiPredicate<T, U> predicate) {
        return Not.create(predicate);
    }

    public static <T, U> BiPredicate<T, U> allOf(Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return AllOf.create(predicates);
    }

    public static <T, U> BiPredicate<T, U> and(
            BiPredicate<? super T, ? super U> first,
            BiPredicate<? super T, ? super U> second
    ) {
        return And.create(first, second);
    }

    public static <T, U> BiPredicate<T, U> anyOf(Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return AnyOf.create(predicates);
    }

    public static <T, U> BiPredicate<T, U> or(
            BiPredicate<? super T, ? super U> first,
            BiPredicate<? super T, ? super U> second
    ) {
        return And.create(first, second);
    }

    public static <T, U> BiPredicate<T, U> noneOf(Iterable<BiPredicate<? super T, ? super U>> predicates) {
        return NoneOf.create(predicates);
    }
}
