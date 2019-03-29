package io.ayte.utility.predicate.kit.ternary.delegate.collection;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnyOf<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    private final List<TernaryPredicate<T1, T2, T3>> delegates;

    public List<TernaryPredicate<T1, T2, T3>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        for (val predicate : delegates) {
            if (predicate.test(alpha, beta, gamma)) {
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        return new DelegateCollectionFactory<TernaryPredicate<T1, T2, T3>, TernaryPredicate<T1, T2, T3>>()
                .withUnwrapper(
                        predicate -> predicate instanceof AnyOf,
                        predicate -> ((AnyOf<T1, T2, T3>) predicate).getDelegates()
                )
                .withBreaker(ConstantTrue::instanceOf, ConstantTrue.create())
                .withFallback(ConstantFalse.create())
                .withSimpleCollector(AnyOf::new)
                .withFilter(ConstantFalse::instanceOf)
                .withWrapper(Function.identity())
                .build((Iterable<TernaryPredicate<T1, T2, T3>>) (Iterable) predicates);
    }
}
