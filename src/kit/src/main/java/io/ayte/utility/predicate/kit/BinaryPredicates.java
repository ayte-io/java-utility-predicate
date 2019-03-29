package io.ayte.utility.predicate.kit;

import io.ayte.utility.predicate.BinaryPredicate;
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
    
    public static <T> BinaryPredicate<Boolean, T> usingFirst() {
        return UsingFirst.create();
    }
    
    public static <T> BinaryPredicate<T, Boolean> usingSecond() {
        return UsingSecond.create();
    }

    public static <T1, T2> BinaryPredicate<T1, T2> wrap(@NonNull BiPredicate<T1, T2> predicate) {
        return Wrapper.create(predicate);
    }

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

    public static <T1, T2> BinaryPredicate<T1, T2> all(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return AllOf.create(predicates);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> any(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return AnyOf.create(predicates);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> none(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return NoneOf.create(predicates);
    }

    public static <T1, T2> BinaryPredicate<T1, T2> one(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return OneOf.create(predicates);
    }

    public static <T> BinaryPredicate<T, T> inAscendingOrder(@NonNull Comparator<T> comparator) {
        return InAscendingOrder.create(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> inAscendingOrder() {
        return InAscendingOrder.create();
    }

    public static <T> BinaryPredicate<T, T> inStrictAscendingOrder(@NonNull Comparator<T> comparator) {
        return InStrictAscendingOrder.create(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> inStrictAscendingOrder() {
        return InStrictAscendingOrder.create();
    }

    public static <T> BinaryPredicate<T, T> inDescendingOrder(@NonNull Comparator<T> comparator) {
        return InDescendingOrder.create(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> inDescendingOrder() {
        return InDescendingOrder.create();
    }

    public static <T> BinaryPredicate<T, T> inStrictDescendingOrder(@NonNull Comparator<T> comparator) {
        return InStrictDescendingOrder.create(comparator);
    }

    public static <T extends Comparable<T>> BinaryPredicate<T, T> inStrictDescendingOrder() {
        return InStrictDescendingOrder.create();
    }
}
