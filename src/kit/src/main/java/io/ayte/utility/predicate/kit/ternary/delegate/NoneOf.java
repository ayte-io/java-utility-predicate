package io.ayte.utility.predicate.kit.ternary.delegate;

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

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NoneOf<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    private final List<TernaryPredicate<T1, T2, T3>> delegates;

    public List<TernaryPredicate<T1, T2, T3>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

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
        return new DelegateCollectionFactory<TernaryPredicate<T1, T2, T3>, TernaryPredicate<T1, T2, T3>>()
                .withUnwrapper(
                        predicate -> predicate instanceof NoneOf,
                        predicate -> ((NoneOf<T1, T2, T3>) predicate).getDelegates()
                )
                .withSimpleCollector(NoneOf::new)
                .withFilter(ConstantFalse::instanceOf)
                .withBreaker(ConstantTrue::instanceOf, ConstantFalse.create())
                .withWrapper(Not::create)
                .withFallback(ConstantTrue.create())
                .build((Iterable<TernaryPredicate<T1, T2, T3>>) (Iterable) predicates);
    }
}
