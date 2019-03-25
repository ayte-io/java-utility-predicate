package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.function.BiPredicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnyOf<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private final Iterable<BiPredicate<T1, T2>> delegates;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        for (val predicate : delegates) {
            if (predicate.test(alpha, beta)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings({"unchecked"})
    public static <T1, T2> BiPredicate<T1, T2> create(@NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates) {
        return new DelegateCollectionFactory<BiPredicate<T1, T2>>()
                .withUnwrapper(ConstantFalse::instanceOf, any -> Collections.emptyList())
                .withUnwrapper(AnyOf::instanceOf, predicate -> ((AnyOf<T1, T2>) predicate).delegates)
                .withBreaker(ConstantTrue::instanceOf, ConstantTrue.create())
                .withSimpleCollector(AnyOf::new)
                .build((Iterable<BiPredicate<T1, T2>>) (Iterable) predicates);
    }

    public static <T1, T2> boolean instanceOf(BiPredicate<T1, T2> predicate) {
        return predicate instanceof AnyOf;
    }

    public static <T1, T2> boolean notInstanceOf(BiPredicate<T1, T2> predicate) {
        return !instanceOf(predicate);
    }
}
