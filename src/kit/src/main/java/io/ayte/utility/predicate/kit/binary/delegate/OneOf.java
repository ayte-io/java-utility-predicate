package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.AugmentedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneOf<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private final List<BiPredicate<T1, T2>> delegates;

    public List<BiPredicate<T1, T2>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @Override
    public boolean test(T1 alpha, T2 beta) {
        boolean observed = false;
        for (val predicate : delegates) {
            if (!predicate.test(alpha, beta)) {
                continue;
            }
            if (observed) {
                return false;
            }
            observed = true;
        }
        return observed;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> BinaryPredicate<T1, T2> create(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        Predicate<BiPredicate<T1, T2>> validator = ConstantTrue::notInstanceOf;
        return new DelegateCollectionFactory<BiPredicate<T1, T2>, BinaryPredicate<T1, T2>>()
                .withUnwrapper(
                        predicate -> predicate instanceof OneOf,
                        predicate -> ((OneOf<T1, T2>) predicate).getDelegates()
                )
                .withValidator(validator)
                .withCollector((delegates, violations) -> {
                    if (violations.getOrDefault(validator, 0) > 1) {
                        return ConstantFalse.create();
                    }
                    return new OneOf<>(delegates);
                })
                .withFilter(ConstantFalse::instanceOf)
                .withWrapper(Wrapper::create)
                .withFallback(ConstantFalse.create())
                .build((Iterable<BiPredicate<T1, T2>>) (Iterable) predicates);
    }
}
