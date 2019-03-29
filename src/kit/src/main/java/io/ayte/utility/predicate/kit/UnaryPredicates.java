package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.delegate.AllOf;
import io.ayte.utility.predicate.kit.unary.delegate.And;
import io.ayte.utility.predicate.kit.unary.delegate.AnyOf;
import io.ayte.utility.predicate.kit.unary.delegate.Derivative;
import io.ayte.utility.predicate.kit.unary.delegate.NoneOf;
import io.ayte.utility.predicate.kit.unary.delegate.Not;
import io.ayte.utility.predicate.kit.unary.delegate.Or;
import io.ayte.utility.predicate.kit.unary.delegate.Xor;
import io.ayte.utility.predicate.kit.unary.iterable.Comparing;
import io.ayte.utility.predicate.kit.unary.iterable.Decreases;
import io.ayte.utility.predicate.kit.unary.iterable.ElementOf;
import io.ayte.utility.predicate.kit.unary.iterable.Increases;
import io.ayte.utility.predicate.kit.unary.iterable.StrictlyDecreases;
import io.ayte.utility.predicate.kit.unary.iterable.StrictlyIncreases;
import io.ayte.utility.predicate.kit.unary.map.KeyOf;
import io.ayte.utility.predicate.kit.unary.map.ValueOf;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
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
public class UnaryPredicates {
    public static <T> UnaryPredicate<T> constantTrue() {
        return ConstantTrue.create();
    }

    public static <T> UnaryPredicate<T> constantFalse() {
        return ConstantFalse.create();
    }

    public static <T> UnaryPredicate<T> constant(boolean value) {
        return value ? constantTrue() : constantFalse();
    }

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

    public static <T> UnaryPredicate<T> all(@NonNull Iterable<Predicate<? super T>> predicates) {
        return AllOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> any(@NonNull Iterable<Predicate<? super T>> predicates) {
        return AnyOf.create(predicates);
    }

    public static <T> UnaryPredicate<T> none(@NonNull Iterable<Predicate<? super T>> predicates) {
        return NoneOf.create(predicates);
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

    public static <T> UnaryPredicate<T> elementOf(@NonNull Collection<? super T> pool) {
        return ElementOf.create(pool);
    }

    public static <T> UnaryPredicate<T> keyOf(@NonNull Map<? super T, ?> pool) {
        return KeyOf.create(pool);
    }

    public static <T> UnaryPredicate<T> valueOf(@NonNull Map<?, ? super T> pool) {
        return ValueOf.create(pool);
    }

    public static <T> UnaryPredicate<T> gt(T reference, @NonNull Comparator<T> comparator) {
        return GreaterThan.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> gt(@NonNull T reference) {
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

    public static <T extends Comparable<T>> UnaryPredicate<T> lt(@NonNull T reference) {
        return LessThan.create(reference);
    }

    public static <T> UnaryPredicate<T> lte(T reference, @NonNull Comparator<T> comparator) {
        return LessThanOrEqualTo.create(reference, comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<T> lte(@NonNull T reference) {
        return LessThanOrEqualTo.create(reference);
    }

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

    public static <T> UnaryPredicate<Iterable<T>> comparing(@NonNull BiPredicate<? super T, ? super T> predicate) {
        return Comparing.create(predicate);
    }

    public static <T> UnaryPredicate<Iterable<T>> increases(@NonNull Comparator<? super T> comparator) {
        return Increases.create(comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<Iterable<T>> increases() {
        return Increases.create();
    }

    public static <T> UnaryPredicate<Iterable<T>> strictlyIncreases(@NonNull Comparator<? super T> comparator) {
        return StrictlyIncreases.create(comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<Iterable<T>> strictlyIncreases() {
        return StrictlyIncreases.create();
    }

    public static <T> UnaryPredicate<Iterable<T>> decreases(@NonNull Comparator<? super T> comparator) {
        return Decreases.create(comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<Iterable<T>> decreases() {
        return Decreases.create();
    }

    public static <T> UnaryPredicate<Iterable<T>> strictlyDecreases(@NonNull Comparator<? super T> comparator) {
        return StrictlyDecreases.create(comparator);
    }

    public static <T extends Comparable<T>> UnaryPredicate<Iterable<T>> strictlyDecreases() {
        return StrictlyDecreases.create();
    }

    public static <T, S> UnaryPredicate<T> derivative(@NonNull Function<T, S> mapper, @NonNull Predicate<S> predicate) {
        return Derivative.create(mapper, predicate);
    }

    public static <T, S> UnaryPredicate<T> mapping(@NonNull Function<T, S> mapper, @NonNull Predicate<S> predicate) {
        return derivative(mapper, predicate);
    }

    public static <T, S> UnaryPredicate<T> extracting(@NonNull Function<T, S> extractor, @NonNull Predicate<S> predicate) {
        return derivative(extractor, predicate);
    }
}
