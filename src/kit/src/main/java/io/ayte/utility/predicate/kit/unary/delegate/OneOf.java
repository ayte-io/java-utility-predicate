package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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
        val converted = new LinkedList<Predicate<T>>();
        for (val predicate : predicates) {
            if (predicate instanceof ConstantFalse) {
                continue;
            }
            converted.add((Predicate<T>) predicate);
        }
        if (converted.isEmpty() || converted.stream().filter(ConstantTrue::instanceOf).count() >= 2) {
            return ConstantFalse.create();
        }
        return new OneOf<>(converted);
    }
}
