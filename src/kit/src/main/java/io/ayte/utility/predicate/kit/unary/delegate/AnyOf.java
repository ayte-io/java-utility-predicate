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
public class AnyOf<T> implements AugmentedUnaryPredicate<T> {
    private final List<Predicate<T>> delegates;

    @Override
    public boolean test(T subject) {
        for (val delegate : delegates) {
            if (delegate.test(subject)) {
                return true;
            }
        }
        return false;
    }

    public List<Predicate<T>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryPredicate<T> create(@NonNull Iterable<Predicate<? super T>> predicates) {
        val factory = new DelegateCollectionFactory<Predicate<T>>();
        factory.withSimpleCollector(AnyOf::new);
        factory.withBreaker(ConstantTrue::instanceOf, ConstantTrue.create());
        factory.withUnwrapper(
                predicate -> predicate instanceof AnyOf,
                predicate -> ((AnyOf) predicate).getDelegates()
        );
        factory.withFilter(ConstantFalse::instanceOf);
        factory.withFallback(ConstantFalse.create());
        return Wrapper.create(factory.build((Iterable) predicates));
    }
}
