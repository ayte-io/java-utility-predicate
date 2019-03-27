package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.ternary.delegate.AllOf;
import io.ayte.utility.predicate.kit.ternary.delegate.And;
import io.ayte.utility.predicate.kit.ternary.delegate.AnyOf;
import io.ayte.utility.predicate.kit.ternary.delegate.NoneOf;
import io.ayte.utility.predicate.kit.ternary.delegate.Not;
import io.ayte.utility.predicate.kit.ternary.delegate.OneOf;
import io.ayte.utility.predicate.kit.ternary.delegate.Or;
import io.ayte.utility.predicate.kit.ternary.delegate.Xor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> not(@NonNull TernaryPredicate<T1, T2, T3> predicate) {
        return Not.create(predicate);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> allOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AllOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> anyOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AnyOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> noneOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return NoneOf.create(predicates);
    }

    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> oneOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return OneOf.create(predicates);
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
