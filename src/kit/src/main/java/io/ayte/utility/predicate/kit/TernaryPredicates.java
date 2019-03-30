package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AllArgumentsCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AlphaBetaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AlphaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.BetaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.GammaCapturedTernaryPredicate;
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

import java.util.function.BooleanSupplier;

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

    /**
     * @param <T1> Predicate argument 2 type.
     * @param <T2> Predicate argument 3 type.
     * @return Predicate that simply returns back first passed argument.
     * This predicate is null-safe meaning passed null will result in
     * {@code false} response, but not in NPE.
     *
     * @since 0.1.0
     */
    public static <T1, T2> TernaryPredicate<Boolean, T1, T2> usingFirst() {
        return UsingFirst.create();
    }

    /**
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 3 type.
     * @return Predicate that simply returns back second passed
     * argument. This predicate is null-safe meaning passed null will
     * result in {@code false} response, but not in NPE.
     *
     * @since 0.1.0
     */
    public static <T1, T2> TernaryPredicate<T1, Boolean, T2> usingSecond() {
        return UsingSecond.create();
    }

    /**
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 2 type.
     * @return Predicate that simply returns back third passed argument.
     * This predicate is null-safe meaning passed null will result in
     * {@code false} response, but not in NPE.
     *
     * @since 0.1.0
     */
    public static <T1, T2> TernaryPredicate<T1, T2, Boolean> usingThird() {
        return UsingThird.create();
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 2 type.
     * @param <T3> Predicate argument 3 type.
     * @return Predicate that returns true only if all passed predicates
     * are satisfied.
     *
     * @since 0.1.0
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> allOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AllOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 2 type.
     * @param <T3> Predicate argument 3 type.
     * @return Predicate that returns true if any of passed predicates
     * is satisfied.
     *
     * @since 0.1.0
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> anyOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return AnyOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 2 type.
     * @param <T3> Predicate argument 3 type.
     * @return Predicate that returns true only if none of passed
     * predicates are satisfied.
     *
     * @since 0.1.0
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> noneOf(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return NoneOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> Predicate argument 1 type.
     * @param <T2> Predicate argument 2 type.
     * @param <T3> Predicate argument 3 type.
     * @return Predicate that returns true only if single one of passed
     * predicates is satisfied.
     *
     * @since 0.1.0
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> oneOf(
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

    public static <T1, T2, T3> BinaryPredicate<T2, T3> captureAlpha(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T1 alpha
    ) {
        return AlphaCapturedTernaryPredicate.create(predicate, alpha);
    }

    public static <T1, T2, T3> BinaryPredicate<T1, T3> captureBeta(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T2 beta
    ) {
        return BetaCapturedTernaryPredicate.create(predicate, beta);
    }

    public static <T1, T2, T3> BinaryPredicate<T1, T2> captureGamma(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T3 gamma
    ) {
        return GammaCapturedTernaryPredicate.create(predicate, gamma);
    }

    public static <T1, T2, T3> BinaryPredicate<T2, T3> capture(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T1 alpha
    ) {
        return captureAlpha(predicate, alpha);
    }

    public static <T1, T2, T3> UnaryPredicate<T3> capture(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T1 alpha,
            T2 beta
    ) {
        return AlphaBetaCapturedTernaryPredicate.create(predicate, alpha, beta);
    }

    public static <T1, T2, T3> BooleanSupplier capture(
            @NonNull TernaryPredicate<T1, T2, T3> predicate,
            T1 alpha,
            T2 beta,
            T3 gamma
    ) {
        return AllArgumentsCapturedTernaryPredicate.create(predicate, alpha, beta, gamma);
    }
}
