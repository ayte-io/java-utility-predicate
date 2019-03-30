package io.ayte.utility.predicate.kit.unary.delegate.collection;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.delegate.Not;
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
public class NoneOf<T> implements AugmentedUnaryPredicate<T> {
    private final List<Predicate<T>> delegates;

    public List<Predicate<T>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @Override
    public boolean test(T subject) {
        for (val predicate : delegates) {
            if (predicate.test(subject)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    public static <T> UnaryPredicate<T> create(@NonNull Iterable<Predicate<? super T>> predicates) {
        return new DelegateCollectionFactory<Predicate<T>, UnaryPredicate<T>>()
                .withSimpleCollector(NoneOf::new)
                .withFlattener(
                        predicate -> predicate instanceof NoneOf,
                        predicate -> ((NoneOf<T>) predicate).delegates
                )
                .withFilter(ConstantFalse::instanceOf)
                .withBreaker(ConstantTrue::instanceOf, ConstantFalse.create())
                .withFallback(ConstantTrue.create())
                .withWrapper(Not::create)
                .build((Iterable<Predicate<T>>) (Iterable) predicates);
    }
}
