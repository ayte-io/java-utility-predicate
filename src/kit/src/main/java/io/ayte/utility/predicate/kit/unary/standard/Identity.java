package io.ayte.utility.predicate.kit.unary.standard;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Identity implements AugmentedUnaryPredicate<Boolean> {
    private static final Identity INSTANCE = new Identity();

    @Override
    public boolean test(Boolean subject) {
        return subject;
    }

    public static UnaryPredicate<Boolean> create() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Identity";
    }
}
