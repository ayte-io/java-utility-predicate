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

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnyOf<T1, T2> implements AugmentedBinaryPredicate<T1, T2> {
    private final List<BiPredicate<T1, T2>> delegates;

    public List<BiPredicate<T1, T2>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

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
    public static <T1, T2> BinaryPredicate<T1, T2> create(
            @NonNull Iterable<BiPredicate<? super T1, ? super T2>> predicates
    ) {
        return new DelegateCollectionFactory<BiPredicate<T1, T2>, BinaryPredicate<T1, T2>>()
                .withUnwrapper(ConstantFalse::instanceOf, any -> Collections.emptyList())
                .withUnwrapper(
                        predicate -> predicate instanceof AnyOf,
                        predicate -> ((AnyOf<T1, T2>) predicate).getDelegates()
                )
                .withBreaker(ConstantTrue::instanceOf, ConstantTrue.create())
                .withSimpleCollector(AnyOf::new)
                .withFallback(ConstantFalse.create())
                .withWrapper(Wrapper::create)
                .build((Iterable<BiPredicate<T1, T2>>) (Iterable) predicates);
    }
}
