package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.AllArgumentsCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.AlphaCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.BetaCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.delegate.Wrapper;
import io.ayte.utility.predicate.kit.binary.delegate.Xor;
import io.ayte.utility.predicate.kit.binary.delegate.collection.OneOf;
import io.ayte.utility.predicate.kit.binary.delegate.Or;
import io.ayte.utility.predicate.kit.binary.sortable.InAscendingOrder;
import io.ayte.utility.predicate.kit.binary.sortable.InDescendingOrder;
import io.ayte.utility.predicate.kit.binary.sortable.InStrictAscendingOrder;
import io.ayte.utility.predicate.kit.binary.sortable.InStrictDescendingOrder;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.binary.delegate.collection.AllOf;
import io.ayte.utility.predicate.kit.binary.delegate.And;
import io.ayte.utility.predicate.kit.binary.delegate.collection.AnyOf;
import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.delegate.collection.NoneOf;
import io.ayte.utility.predicate.kit.binary.standard.UsingFirst;
import io.ayte.utility.predicate.kit.binary.standard.UsingSecond;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;

// CLOVER:OFF

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryPredicates {
    public static <T1, T2> BinaryPredicate<T1, T2> constant(boolean value) {
        return value ? constantTrue() : constantFalse();
    }

    public static <T1, T2> BinaryPredicate<T1, T2> constantTrue() {
        return ConstantTrue.create();
    }

    public static <T1, T2> BinaryPredicate<T1, T2> constantFalse() {
        return ConstantFalse.create();
    }

    /**
     * @param <T> Second predicate argument type.
     * @return A predicate that returns first passed argument. This
     * predicate is null-safe, meaning that passing null as argument
     * will produce {@code false} response instead of NPE.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<Boolean, T> usingFirst() {
        return UsingFirst.create();
    }

    /**
     * @param <T> First predicate argument type.
     * @return A predicate that returns second passed argument. This
     * predicate is null-safe, meaning that passing null as argument
     * will produce {@code false} response instead of NPE.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<T, Boolean> usingSecond() {
        return UsingSecond.create();
    }

    /**
     * @param predicate Delegate predicate
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Wraps predicate in additional wrapper (if necessary) so
     * returned predicate is of type {@link BinaryPredicate}.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> wrap(@NonNull BiPredicate<T1, T2> predicate) {
        return Wrapper.create(predicate);
    }

    /**
     * @param predicate Predicate to be inverted.
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Predicate that returns inverted value.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> not(@NonNull BiPredicate<T1, T2> predicate) {
        return Not.create(predicate);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> and(
            @NonNull BiPredicate<? super T1, ? super T2> first,
            @NonNull BiPredicate<? super T1, ? super T2> second
    ) {
        return And.create(first, second);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> or(
            @NonNull BiPredicate<? super T1, ? super T2> first,
            @NonNull BiPredicate<? super T1, ? super T2> second
    ) {
        return Or.create(first, second);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> xor(
            @NonNull BiPredicate<? super T1, ? super T2> first,
            @NonNull BiPredicate<? super T1, ? super T2> second
    ) {
        return Xor.create(first, second);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Predicate that returns true if all of passed predicates
     * are satisfied against tested values.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> allOf(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        return AllOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Predicate that returns true if any of passed predicates
     * is satisfied against tested values.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> anyOf(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        return AnyOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Predicate that returns true only if none of delegate
     * predicates are satisfied against tested values.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> noneOf(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        return NoneOf.create(predicates);
    }

    /**
     * @param predicates Delegate predicates.
     * @param <T1> First predicate argument type.
     * @param <T2> Second predicate argument type.
     * @return Predicate that returns true only if single delegate
     * predicate is satisfied against tested values.
     *
     * @since 0.1.0
     */
    public static <T1, T2> BinaryPredicate<T1, T2> oneOf(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        return OneOf.create(predicates);
    }

    /**
     * @param comparator Comparator to compare values.
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * greater than or equal to first one.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<T, T> inAscendingOrder(@NonNull Comparator<T> comparator) {
        return InAscendingOrder.create(comparator);
    }

    /**
     * @see #inAscendingOrder(Comparator)
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * greater than or equal to first one.
     *
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> BinaryPredicate<T, T> inAscendingOrder() {
        return InAscendingOrder.create();
    }

    /**
     * @param comparator Comparator to compare values.
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * greater than first one.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<T, T> inStrictAscendingOrder(@NonNull Comparator<T> comparator) {
        return InStrictAscendingOrder.create(comparator);
    }

    /**
     * @see #inStrictAscendingOrder(Comparator)
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * greater than first one.
     *
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> BinaryPredicate<T, T> inStrictAscendingOrder() {
        return InStrictAscendingOrder.create();
    }

    /**
     * @param comparator Comparator to compare values.
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * smaller than or equal to first one.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<T, T> inDescendingOrder(@NonNull Comparator<T> comparator) {
        return InDescendingOrder.create(comparator);
    }

    /**
     * @see #inDescendingOrder(Comparator)
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * smaller than or equal to first one.
     *
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> BinaryPredicate<T, T> inDescendingOrder() {
        return InDescendingOrder.create();
    }


    /**
     * @param comparator Comparator to compare values.
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * smaller than first one.
     *
     * @since 0.1.0
     */
    public static <T> BinaryPredicate<T, T> inStrictDescendingOrder(@NonNull Comparator<T> comparator) {
        return InStrictDescendingOrder.create(comparator);
    }

    /**
     * @see #inStrictDescendingOrder(Comparator)
     * @param <T> Argument type.
     * @return Predicate that returns true only if second argument is
     * smaller than first one.
     *
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> BinaryPredicate<T, T> inStrictDescendingOrder() {
        return InStrictDescendingOrder.create();
    }

    public <T1, T2> UnaryPredicate<T2> captureAlpha(@NonNull BiPredicate<T1, T2> predicate, T1 alpha) {
        return AlphaCapturedBinaryPredicate.create(predicate, alpha);
    }

    public <T1, T2> UnaryPredicate<T1> captureBeta(@NonNull BiPredicate<T1, T2> predicate, T2 beta) {
        return BetaCapturedBinaryPredicate.create(predicate, beta);
    }

    public <T1, T2> BooleanSupplier capture(@NonNull BiPredicate<T1, T2> predicate, T1 alpha, T2 beta) {
        return AllArgumentsCapturedBinaryPredicate.create(predicate, alpha, beta);
    }
}
