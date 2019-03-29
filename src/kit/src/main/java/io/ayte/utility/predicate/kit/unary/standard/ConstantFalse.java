package io.ayte.utility.predicate.kit.unary.standard;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantFalse<T> implements AugmentedUnaryPredicate<T> {
    private static final ConstantFalse INSTANCE = new ConstantFalse<>();

    @Override
    public boolean test(T subject) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryPredicate<T> create() {
        return INSTANCE;
    }

    public static <T> boolean instanceOf(Predicate<T> predicate) {
        return predicate instanceof ConstantFalse;
    }

    @Override
    public String toString() {
        return "ConstantFalse";
    }
}
