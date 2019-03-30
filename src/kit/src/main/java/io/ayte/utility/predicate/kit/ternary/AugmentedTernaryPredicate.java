package io.ayte.utility.predicate.kit.ternary;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AllArgumentsCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AlphaBetaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.AlphaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.BetaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.capture.GammaCapturedTernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.delegate.And;
import io.ayte.utility.predicate.kit.ternary.delegate.Not;
import io.ayte.utility.predicate.kit.ternary.delegate.Or;
import io.ayte.utility.predicate.kit.ternary.delegate.Xor;

import java.util.function.BooleanSupplier;

public interface AugmentedTernaryPredicate<T1, T2, T3> extends TernaryPredicate<T1, T2, T3> {
    @Override
    default TernaryPredicate<T1, T2, T3> and(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return And.create(this, other);
    }

    @Override
    default TernaryPredicate<T1, T2, T3> or(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return Or.create(this, other);
    }

    @Override
    default TernaryPredicate<T1, T2, T3> negate() {
        return Not.create(this);
    }

    @Override
    default TernaryPredicate<T1, T2, T3> xor(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return Xor.create(this, other);
    }

    @Override
    default BinaryPredicate<T2, T3> captureAlpha(T1 alpha) {
        return AlphaCapturedTernaryPredicate.create(this, alpha);
    }

    @Override
    default BinaryPredicate<T1, T3> captureBeta(T2 beta) {
        return BetaCapturedTernaryPredicate.create(this, beta);
    }

    @Override
    default BinaryPredicate<T1, T2> captureGamma(T3 gamma) {
        return GammaCapturedTernaryPredicate.create(this, gamma);
    }

    @Override
    default UnaryPredicate<T3> capture(T1 alpha, T2 beta) {
        return AlphaBetaCapturedTernaryPredicate.create(this, alpha, beta);
    }

    @Override
    default BooleanSupplier capture(T1 alpha, T2 beta, T3 gamma) {
        return AllArgumentsCapturedTernaryPredicate.create(this, alpha, beta, gamma);
    }
}
