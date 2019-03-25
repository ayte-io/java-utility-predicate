package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.function.BiPredicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllOf<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    @Getter(AccessLevel.PRIVATE)
    private final Iterable<BiPredicate<T1, T2>> predicates;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        for (val predicate : predicates) {
            if (!predicate.test(alpha, beta)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> BiPredicate<T1, T2> create(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return new DelegateCollectionFactory<BiPredicate<T1, T2>>()
                .withUnwrapper(ConstantTrue::instanceOf, any -> Collections.emptyList())
                .withUnwrapper(AllOf::instanceOf, predicate -> ((AllOf<T1, T2>) predicate).getPredicates())
                .withBreaker(ConstantFalse::instanceOf, ConstantFalse.create())
                .withSimpleCollector(AllOf::new)
                .build((Iterable<BiPredicate<T1, T2>>) (Iterable) predicates);
    }

    public static <T1, T2> boolean instanceOf(BiPredicate<T1, T2> predicate) {
        return predicate instanceof AllOf;
    }

    public static <T1, T2> boolean notInstanceOf(BiPredicate<T1, T2> predicate) {
        return !instanceOf(predicate);
    }
}
