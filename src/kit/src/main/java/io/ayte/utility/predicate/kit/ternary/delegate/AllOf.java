package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.ConstantFalse;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllOf<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    @Getter
    private final Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> delegates;

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        for (val delegate : delegates) {
            if (!delegate.test(alpha, beta, gamma)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        val factory = new DelegateCollectionFactory<TernaryPredicate<? super T1, ? super T2, ? super T3>>();
        factory.withSimpleCollector(AllOf::new);
        factory.withUnwrapper(predicate -> predicate instanceof AllOf, predicate -> ((AllOf) predicate).getDelegates());
        factory.withBreaker(ConstantFalse::instanceOf, ConstantFalse.create());
        factory.withFallback(ConstantFalse.create());
        return (TernaryPredicate<T1, T2, T3>) factory.build(predicates);
    }
}
