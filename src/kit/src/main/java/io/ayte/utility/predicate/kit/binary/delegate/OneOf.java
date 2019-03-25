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

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneOf<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    @Getter
    private final List<BiPredicate<T1, T2>> delegates;

    @Override
    public boolean test(T1 alpha, T2 beta) {
        boolean state = false;
        for (val predicate : delegates) {
            if (!predicate.test(alpha, beta)) {
                continue;
            }
            if (state) {
                return false;
            }
            state = true;
        }
        return state;
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2> BiPredicate<T1, T2> create(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        Predicate<BiPredicate<T1, T2>> validator = ConstantTrue::notInstanceOf;
        return new DelegateCollectionFactory<BiPredicate<T1, T2>>()
                .withUnwrapper(OneOf::instanceOf, predicate -> ((OneOf<T1, T2>) predicate).delegates)
                .withValidator(validator)
                .withCollector((delegates, violations) -> {
                    if (violations.getOrDefault(validator, 0) > 1){
                        return ConstantFalse.create();
                    }
                    return new OneOf<>(delegates);
                })
                .build((Iterable<BiPredicate<T1, T2>>) (Iterable) predicates);
    }

    public static <T1, T2> boolean instanceOf(BiPredicate<T1, T2> predicate) {
        return predicate instanceof OneOf;
    }

    public static <T1, T2> boolean notInstanceOf(BiPredicate<T1, T2> predicate) {
        return !instanceOf(predicate);
    }
}
