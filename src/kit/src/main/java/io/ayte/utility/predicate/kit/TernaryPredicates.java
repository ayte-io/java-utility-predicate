package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.ternary.delegate.collection.AllOf;
import io.ayte.utility.predicate.kit.ternary.delegate.And;
import io.ayte.utility.predicate.kit.ternary.delegate.collection.AnyOf;
import io.ayte.utility.predicate.kit.ternary.delegate.collection.NoneOf;
import io.ayte.utility.predicate.kit.ternary.delegate.Not;
import io.ayte.utility.predicate.kit.ternary.delegate.collection.OneOf;
import io.ayte.utility.predicate.kit.ternary.delegate.Or;
import io.ayte.utility.predicate.kit.ternary.delegate.Xor;
import io.ayte.utility.predicate.kit.ternary.standard.UsingFirst;
import io.ayte.utility.predicate.kit.ternary.standard.UsingSecond;
import io.ayte.utility.predicate.kit.ternary.standard.UsingThird;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

// CLOVER:OFF

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TernaryPredicates {
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> constant(boolean value) {
        return value ? constantTrue() : constantFalse();
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> constantTrue() {
        return ConstantTrue.create();
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> constantFalse() {
        return ConstantFalse.create();
    }

    public static <T1, T2> TernaryPredicate<Boolean, T1, T2> usingFirst() {
        return UsingFirst.create();
    }

    public static <T1, T2> TernaryPredicate<T1, Boolean, T2> usingSecond() {
        return UsingSecond.create();
    }

    public static <T1, T2> TernaryPredicate<T1, T2, Boolean> usingThird() {
        return UsingThird.create();
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> all(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AllOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> any(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AnyOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> none(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return NoneOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> one(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return OneOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> not(@NonNull TernaryPredicate<T1, T2, T3> predicate) {
        return Not.create(predicate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> and(
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> first,
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> second
    ) {
        return And.create(first, second);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> or(
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> first,
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> second
    ) {
        return Or.create(first, second);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> xor(
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> first,
            @NonNull TernaryPredicate<? super T1, ? super T2, ? super T3> second
    ) {
        return Xor.create(first, second);
    }
}
