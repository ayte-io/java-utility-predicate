package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
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
public class OneOf<T> implements AugmentedUnaryPredicate<T> {
    private final List<Predicate<T>> delegates;

    public List<Predicate<T>> getDelegates() {
        return Collections.unmodifiableList(delegates);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean test(T subject) {
        boolean encountered = false;
        for (val predicate : delegates) {
            if (!predicate.test(subject)) {
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
    public static <T> Predicate<T> create(@NonNull Iterable<Predicate<? super T>> predicates) {
        Predicate<Predicate<T>> validator = ConstantTrue::notInstanceOf;
        return new DelegateCollectionFactory<Predicate<T>, UnaryPredicate<T>>()
                .withFilter(ConstantFalse::instanceOf)
                .withFallback(ConstantFalse.create())
                .withWrapper(Wrapper::create)
                .withValidator(validator)
                .withCollector((results, violations) -> {
                    if (violations.getOrDefault(validator, 0) < 2) {
                        return new OneOf<>(results);
                    }
                    return ConstantFalse.create();
                })
                .build((Iterable<Predicate<T>>) (Iterable) predicates);
    }
}
