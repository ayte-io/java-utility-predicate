package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.List;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneOf<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {
    @Getter(AccessLevel.PRIVATE)
    private final List<TernaryPredicate<? super T1, ? super T2, ? super T3>> delegates;

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
        TernaryPredicate result = new DelegateCollectionFactory<TernaryPredicate<? super T1, ? super T2, ? super T3>>()
                .withFallback(ConstantFalse.create())
                .withValidator(ConstantTrue::notInstanceOf)
                .withCollector((pds, violations) -> {
                    if (violations.values().iterator().next() <= 1) {
                        return new OneOf<>(pds);
                    }
                    return ConstantFalse.create();
                })
                .build(predicates);
        return (TernaryPredicate<T1, T2, T3>) result;
    }
}
