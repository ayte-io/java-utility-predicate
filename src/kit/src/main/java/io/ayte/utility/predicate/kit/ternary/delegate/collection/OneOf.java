package io.ayte.utility.predicate.kit.ternary.delegate.collection;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.AugmentedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneOf<T1, T2, T3> implements AugmentedTernaryPredicate<T1, T2, T3> {
    private final List<TernaryPredicate<T1, T2, T3>> delegates;

    public List<TernaryPredicate<T1, T2, T3>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @Override
    public boolean test(T1 alpha, T2 beta, T3 gamma) {
        boolean encountered = false;
        for (val predicate : delegates) {
            if (!predicate.test(alpha, beta, gamma)) {
                continue;
            }
            if (encountered) {
                return false;
            }
            encountered = true;
        }
        return encountered;
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> create(
            Iterable<TernaryPredicate<? super T1, ? super T2, ? super T3>> predicates
    ) {
        Predicate<TernaryPredicate<T1, T2, T3>> validator = ConstantTrue::notInstanceOf;
        return new DelegateCollectionFactory<TernaryPredicate<T1, T2, T3>, TernaryPredicate<T1, T2, T3>>()
                .withFallback(ConstantFalse.create())
                .withFilter(ConstantFalse::instanceOf)
                .withValidator(validator)
                .withCollector((pds, violations) -> {
                    if (violations.getOrDefault(validator, 0) < 2) {
                        return new OneOf<>(pds);
                    }
                    return ConstantFalse.create();
                })
                .withUnwrapper(
                        predicate -> predicate instanceof OneOf,
                        predicate -> ((OneOf<T1, T2, T3>) predicate).getDelegates()
                )
                .withWrapper(Function.identity())
                .build((Iterable<TernaryPredicate<T1, T2, T3>>) (Iterable) predicates);
    }
}
