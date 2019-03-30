package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.delegate.collection.AllOf;
import io.ayte.utility.predicate.kit.unary.delegate.And;
import io.ayte.utility.predicate.kit.unary.delegate.collection.AnyOf;
import io.ayte.utility.predicate.kit.unary.delegate.Computing;
import io.ayte.utility.predicate.kit.unary.delegate.collection.NoneOf;
import io.ayte.utility.predicate.kit.unary.delegate.Not;
import io.ayte.utility.predicate.kit.unary.delegate.collection.OneOf;
import io.ayte.utility.predicate.kit.unary.delegate.Or;
import io.ayte.utility.predicate.kit.unary.delegate.Wrapper;
import io.ayte.utility.predicate.kit.unary.delegate.Xor;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.AllElementsEqual;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.AllElementsMatch;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.AnyElementEquals;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.AnyElementMatches;
import io.ayte.utility.predicate.kit.unary.iterable.Comparing;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.OneElementMatches;
import io.ayte.utility.predicate.kit.unary.iterable.order.Decreases;
import io.ayte.utility.predicate.kit.unary.iterable.ElementOf;
import io.ayte.utility.predicate.kit.unary.iterable.order.Increases;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.NoneElementsEqual;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.NoneElementsMatch;
import io.ayte.utility.predicate.kit.unary.iterable.aggregate.OneElementEquals;
import io.ayte.utility.predicate.kit.unary.iterable.order.StrictlyDecreases;
import io.ayte.utility.predicate.kit.unary.iterable.order.StrictlyIncreases;
import io.ayte.utility.predicate.kit.unary.map.KeyOf;
import io.ayte.utility.predicate.kit.unary.map.ValueOf;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.unary.standard.Identity;
import io.ayte.utility.predicate.kit.unary.value.EqualTo;
import io.ayte.utility.predicate.kit.unary.value.EqualToInAnyCase;
import io.ayte.utility.predicate.kit.unary.value.GreaterThan;
import io.ayte.utility.predicate.kit.unary.value.GreaterThanOrEqualTo;
import io.ayte.utility.predicate.kit.unary.value.LessThan;
import io.ayte.utility.predicate.kit.unary.value.LessThanOrEqualTo;
import io.ayte.utility.predicate.kit.unary.value.NotEqualTo;
import io.ayte.utility.predicate.kit.unary.value.NotEqualToInAnyCase;
import io.ayte.utility.predicate.kit.unary.value.Within;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

// CLOVER:OFF

@SuppressWarnings({"squid:S1452", "unused"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Predicates {
    /**
     * @param <T> Predicate argument type.
     * @return Predicate that always returns true
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> constantTrue() {
        return ConstantTrue.create();
    }

    /**
     * @param <T> Predicate argument type.
     * @return Predicate that always returns false.
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> constantFalse() {
        return ConstantFalse.create();
    }

    /**
     * @param value Value for predicate to return.
     * @param <T> Predicate argument type.
     * @return Predicate that always returns provided value.
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> constant(boolean value) {
        return value ? constantTrue() : constantFalse();
    }

    /**
     * @return Predicate that passes value through. Null values are
     * treated as {@code false}.
     *
     * @since 0.1.0
     */
    public static UnaryPredicate<Boolean> identity() {
        return Identity.create();
    }

    /**
     * Wraps predicate (if necessary) so it can be used as
     * {@link UnaryPredicate}.
     *
     * @param predicate Predicate to wrap.
     * @param <T> Predicate argument type.
     * @return Wrapped predicate (if it doesn't implement
     * {@link UnaryPredicate}) or casted provided predicate (if it
     * implements {@link UnaryPredicate}).
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> wrap(@NonNull Predicate<T> predicate) {
        return Wrapper.create(predicate);
    }

    /**
     * @param predicate Predicate to inverse.
     * @param <T> Predicate argument type.
     * @return Predicate whose return value is inverse of passed one.
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> not(@NonNull Predicate<T> predicate) {
        return Not.create(predicate);
    }

    public static <T> UnaryPredicate<T> and(@NonNull Predicate<? super T> first, @NonNull Predicate<? super T> second) {
        return And.create(first, second);
    }

    public static <T> UnaryPredicate<T> or(@NonNull Predicate<? super T> first, @NonNull Predicate<? super T> second) {
        return Or.create(first, second);
    }

    public static <T> UnaryPredicate<T> xor(@NonNull Predicate<? super T> first, @NonNull Predicate<? super T> second) {
        return Xor.create(first, second);
    }

    public static <T> UnaryPredicate<T> allOf(@NonNull Iterable<Predicate<? super T>> predicates) {
        return AllOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> anyOf(@NonNull Iterable<Predicate<? super T>> predicates) {
        return AnyOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> noneOf(@NonNull Iterable<Predicate<? super T>> predicates) {
        return NoneOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> oneOf(@NonNull Iterable<Predicate<? super T>> predicates) {
        return OneOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> eq(Object reference) {
        return EqualTo.create(reference);
    }

    public static UnaryPredicate<String> equalToInAnyCase(@NonNull String reference) {
        return EqualToInAnyCase.create(reference);
    }

    public static UnaryPredicate<String> eqAnyCase(String reference) {
        return equalToInAnyCase(reference);
    }

    public static <T> UnaryPredicate<T> neq(Object reference) {
        return NotEqualTo.create(reference);
    }

    public static UnaryPredicate<String> notEqualToInAnyCase(@NonNull String reference) {
        return NotEqualToInAnyCase.create(reference);
    }

    public static UnaryPredicate<String> neqAnyCase(@NonNull String reference) {
        return notEqualToInAnyCase(reference);
    }

    public static <T> UnaryPredicate<T> gt(T reference, @NonNull Comparator<T> comparator) {
        return GreaterThan.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> gt(T reference) {
        return GreaterThan.create(reference);
    }

    public static <T> UnaryPredicate<T> gte(T reference, @NonNull Comparator<T> comparator) {
        return GreaterThanOrEqualTo.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> gte(T reference) {
        return GreaterThanOrEqualTo.create(reference);
    }

    public static <T> UnaryPredicate<T> lt(T reference, @NonNull Comparator<T> comparator) {
        return LessThan.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> lt(T reference) {
        return LessThan.create(reference);
    }

    public static <T> UnaryPredicate<T> lte(T reference, @NonNull Comparator<T> comparator) {
        return LessThanOrEqualTo.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> lte(T reference) {
        return LessThanOrEqualTo.create(reference);
    }

    /**
     * @param lower Range lower bound.
     * @param upper Range upper bound.
     * @param lowerInclusive Whether lower bound is inclusive.
     * @param upperInclusive Whether upper bound is inclusive.
     * @param comparator Comparator to execute range checks.
     * @param <T> Argument type.
     * @return Predicate that verifies that supplied value lies within
     * specified range.
     *
     * @since 0.1.0
     */
    public static <T> UnaryPredicate<T> within(
            T lower,
            T upper,
            boolean lowerInclusive,
            boolean upperInclusive,
            @NonNull Comparator<T> comparator
    ) {
        return Within.create(lower, lowerInclusive, upper, upperInclusive, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> within(
            T lower,
            T upper,
            boolean lowerInclusive,
            boolean upperInclusive
    ) {
        return Within.create(lower, lowerInclusive, upper, upperInclusive);
    }

    public static <T> UnaryPredicate<T> withinInclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, false, upper, true, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinInclusive(T lower, T upper) {
        return Within.create(lower, false, upper, false);
    }

    public static <T> UnaryPredicate<T> withinExclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, true, upper, true, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinExclusive(T lower, T upper) {
        return Within.create(lower, true, upper, true);
    }

    public static <T> UnaryPredicate<T> withinLowerExclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, true, upper, false, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinLowerExclusive(T lower, T upper) {
        return Within.create(lower, true, upper, false);
    }

    public static <T> UnaryPredicate<T> withinLowerInclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, true, upper, false, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinLowerInclusive(T lower, T upper) {
        return Within.create(lower, false, upper, true);
    }

    public static <T> UnaryPredicate<T> withinUpperExclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, false, upper, true, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinUpperExclusive(T lower, T upper) {
        return Within.create(lower, false, upper, true);
    }

    public static <T> UnaryPredicate<T> withinUpperInclusive(T lower, T upper, @NonNull Comparator<T> comparator) {
        return Within.create(lower, true, upper, false, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> withinUpperInclusive(T lower, T upper) {
        return Within.create(lower, true, upper, false);
    }

    public static <T> UnaryPredicate<T> keyOf(@NonNull Map<? super T, ?> pool) {
        return KeyOf.create(pool);
    }

    public static <T> UnaryPredicate<T> valueOf(@NonNull Map<?, ? super T> pool) {
        return ValueOf.create(pool);
    }

    public static <T> UnaryPredicate<T> elementOf(@NonNull Collection<? super T> pool) {
        return ElementOf.create(pool);
    }

    public static <E> UnaryPredicate<Iterable<E>> allEq(Object reference) {
        return AllElementsEqual.create(reference);
    }

    public static <E> UnaryPredicate<Iterable<E>> allMatch(@NonNull Predicate<? super E> predicate) {
        return AllElementsMatch.create(predicate);
    }

    public static <E> UnaryPredicate<Iterable<E>> anyEq(Object reference) {
        return AnyElementEquals.create(reference);
    }

    public static <E> UnaryPredicate<Iterable<E>> anyMatches(@NonNull Predicate<? super E> predicate) {
        return AnyElementMatches.create(predicate);
    }

    public static <E> UnaryPredicate<Iterable<E>> noneEq(Object reference) {
        return NoneElementsEqual.create(reference);
    }

    public static <E> UnaryPredicate<Iterable<E>> noneMatch(@NonNull Predicate<? super E> predicate) {
        return NoneElementsMatch.create(predicate);
    }

    public static <E> UnaryPredicate<Iterable<E>> oneEq(Object reference) {
        return OneElementEquals.create(reference);
    }

    public static <E> UnaryPredicate<Iterable<E>> oneMatches(@NonNull Predicate<E> predicate) {
        return OneElementMatches.create(predicate);
    }

    /**
     * This method creates a special predicate that checks every two
     * consequent elements of supplied iterable. Delegate predicate is
     * fed with such pairs of elements, and resulting value is true only
     * if delegate predicate has returned true for every pair of
     * elements.
     *
     * @param predicate Delegate predicate.
     * @param <E> Type of iterable elements.
     * @return Predicate that returns true only if delegate predicate
     * is satisfied for every pair of two consequent elements in
     * iterable.
     *
     * @since 0.1.0
     */
    public static <E> UnaryPredicate<Iterable<E>> comparing(@NonNull BiPredicate<? super E, ? super E> predicate) {
        return Comparing.create(predicate);
    }

    /**
     * @param comparator Comparator to perform element checks.
     * @param <E> Type of iterable elements.
     * @return Predicate that returns true only if every consequent
     * element of iterable is greater than or equal to previous one.
     *
     * @since 0.1.0
     */
    public static <E> UnaryPredicate<Iterable<E>> increases(@NonNull Comparator<? super E> comparator) {
        return Increases.create(comparator);
    }

    public static <E extends Comparable<E>> UnaryPredicate<Iterable<E>> increases() {
        return Increases.create();
    }

    /**
     * @param comparator Comparator to perform element checks.
     * @param <E> Type of iterable elements.
     * @return Predicate that returns true only if every consequent
     * element of iterable is greater than previous one.
     *
     * @since 0.1.0
     */
    public static <E> UnaryPredicate<Iterable<E>> strictlyIncreases(@NonNull Comparator<? super E> comparator) {
        return StrictlyIncreases.create(comparator);
    }

    public static <E extends Comparable<E>> UnaryPredicate<Iterable<E>> strictlyIncreases() {
        return StrictlyIncreases.create();
    }

    /**
     * @param comparator Comparator to perform element checks.
     * @param <E> Type of iterable elements.
     * @return Predicate that returns true only if every consequent
     * element of iterable is smaller than or equal to previous one.
     *
     * @since 0.1.0
     */
    public static <E> UnaryPredicate<Iterable<E>> decreases(@NonNull Comparator<? super E> comparator) {
        return Decreases.create(comparator);
    }

    public static <E extends Comparable<E>> UnaryPredicate<Iterable<E>> decreases() {
        return Decreases.create();
    }

    /**
     * @param comparator Comparator to perform element checks.
     * @param <E> Type of iterable elements.
     * @return Predicate that returns true only if every consequent
     * element of iterable is smaller than previous one.
     *
     * @since 0.1.0
     */
    public static <E> UnaryPredicate<Iterable<E>> strictlyDecreases(@NonNull Comparator<? super E> comparator) {
        return StrictlyDecreases.create(comparator);
    }

    public static <E extends Comparable<E>> UnaryPredicate<Iterable<E>> strictlyDecreases() {
        return StrictlyDecreases.create();
    }

    /**
     * @param mapper Function that performs conversion.
     * @param predicate Delegate predicate that is responsible for
     * generating output value.
     * @param <T> Type of input value.
     * @param <S> Type of delegate argument.
     * @return Predicate that uses provided function to map input value
     * to value for delegate predicate.
     *
     * @since 0.1.0
     */
    public static <T, S> UnaryPredicate<T> computing(@NonNull Function<T, S> mapper, @NonNull Predicate<S> predicate) {
        return Computing.create(mapper, predicate);
    }
}
