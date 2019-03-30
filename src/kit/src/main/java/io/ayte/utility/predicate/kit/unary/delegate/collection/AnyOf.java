package io.ayte.utility.predicate.kit.unary.delegate.collection;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.delegate.Wrapper;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnyOf<T> implements AugmentedUnaryPredicate<T> {
    private final List<Predicate<T>> delegates;

    public List<Predicate<T>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @Override
    public boolean test(T subject) {
        for (val delegate : delegates) {
            if (delegate.test(subject)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryPredicate<T> create(@NonNull Iterable<Predicate<? super T>> predicates) {
        return new DelegateCollectionFactory<Predicate<T>, UnaryPredicate<T>>()
                .withSimpleCollector(AnyOf::new)
                .withFlattener(
                        predicate -> predicate instanceof AnyOf,
                        predicate -> ((AnyOf<T>) predicate).getDelegates()
                )
                .withFilter(ConstantFalse::instanceOf)
                .withBreaker(ConstantTrue::instanceOf, ConstantTrue.create())
                .withFallback(ConstantFalse.create())
                .withWrapper(Wrapper::create)
                .build((Iterable<Predicate<T>>) (Iterable) predicates);
    }
}
