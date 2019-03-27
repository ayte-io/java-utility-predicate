package io.ayte.utility.predicate.kit.unary.standard;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantTrue<T> implements AugmentedUnaryPredicate<T> {
    private static final ConstantTrue INSTANCE = new ConstantTrue<>();

    @Override
    public boolean test(T subject) {
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryPredicate<T> create() {
        return INSTANCE;
    }

    public static <T> boolean instanceOf(Predicate<T> predicate) {
        return predicate instanceof ConstantTrue;
    }

    public static <T> boolean notInstanceOf(Predicate<T> predicate) {
        return !instanceOf(predicate);
    }

    @Override
    public String toString() {
        return "ConstantTrue";
    }
}
