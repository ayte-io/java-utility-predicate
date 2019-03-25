package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NoneOf<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {
    @Getter
    private final List<TernaryPredicate<? super T1, ? super T2, ? super T3>> delegates;

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        for (val delegate : delegates) {
            if (delegate.test(alpha, beta, gamma)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(
            @NonNull Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        val factory = new DelegateCollectionFactory<TernaryPredicate<? super T1, ? super T2, ? super T3>>();
        factory.withSimpleCollector(NoneOf::new);
        factory.withBreaker(ConstantTrue::instanceOf, ConstantFalse.create());
        factory.withFallback(ConstantTrue.create());
        factory.withUnwrapper(
                predicate -> predicate instanceof NoneOf,
                predicate -> ((NoneOf) predicate).getDelegates()
        );
        return (TernaryPredicate<T1, T2, T3>) factory.build(predicates);
    }
}
