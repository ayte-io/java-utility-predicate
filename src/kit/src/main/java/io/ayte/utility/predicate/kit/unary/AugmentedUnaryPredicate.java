package io.ayte.utility.predicate.kit.unary;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.capture.ArgumentCapturedPredicate;
import io.ayte.utility.predicate.kit.unary.delegate.And;
import io.ayte.utility.predicate.kit.unary.delegate.Not;
import io.ayte.utility.predicate.kit.unary.delegate.Or;
import io.ayte.utility.predicate.kit.unary.delegate.Xor;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public interface AugmentedUnaryPredicate<T> extends UnaryPredicate<T> {
    @Override
    default UnaryPredicate<T> and(Predicate<? super T> other) {
        return And.create(this, other);
    }

    @Override
    default UnaryPredicate<T> negate() {
        return Not.create(this);
    }

    @Override
    default UnaryPredicate<T> or(Predicate<? super T> other) {
        return Or.create(this, other);
    }

    @Override
    default UnaryPredicate<T> xor(Predicate<? super T> other) {
        return Xor.create(this, other);
    }

    @Override
    default BooleanSupplier capture(T subject) {
        return ArgumentCapturedPredicate.create(this, subject);
    }
}
