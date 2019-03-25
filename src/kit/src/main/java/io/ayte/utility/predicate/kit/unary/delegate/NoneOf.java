package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.utility.DelegateCollectionFactory;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NoneOf<T> implements AugmentedUnaryPredicate<T> {
    private final List<Predicate<T>> delegates;

    @Override
    public boolean test(T subject) {
        for (val predicate : delegates) {
            if (predicate.test(subject)) {
                return false;
            }
        }
        return true;
    }

    public List<Predicate<T>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    public static <T> UnaryPredicate<T> create(@NonNull Iterable<Predicate<? super T>> predicates) {
        val factory = new DelegateCollectionFactory<Predicate<T>>();
        factory.withSimpleCollector(NoneOf::new);
        factory.withUnwrapper(
                predicate -> predicate instanceof NoneOf,
                predicate -> ((NoneOf) predicate).delegates
        );
        factory.withFilter(ConstantFalse::instanceOf);
        factory.withBreaker(ConstantTrue::instanceOf, ConstantFalse.create());
        factory.withFallback(ConstantTrue.create());
        return Wrapper.create(factory.build((Iterable) predicates));
    }
}
