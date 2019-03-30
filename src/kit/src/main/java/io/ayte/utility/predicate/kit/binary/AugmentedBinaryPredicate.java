package io.ayte.utility.predicate.kit.binary;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.AllArgumentsCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.AlphaCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.capture.BetaCapturedBinaryPredicate;
import io.ayte.utility.predicate.kit.binary.delegate.And;
import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.delegate.Or;
import io.ayte.utility.predicate.kit.binary.delegate.Xor;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;

public interface AugmentedBinaryPredicate<T1, T2> extends BinaryPredicate<T1, T2> {
    @Override
    default BinaryPredicate<T1, T2> and(BiPredicate<? super T1, ? super T2> other) {
        return And.create(this, other);
    }

    @Override
    default BinaryPredicate<T1, T2> negate() {
        return Not.create(this);
    }

    @Override
    default BinaryPredicate<T1, T2> or(BiPredicate<? super T1, ? super T2> other) {
        return Or.create(this, other);
    }

    @Override
    default BinaryPredicate<T1, T2> xor(BiPredicate<? super T1, ? super T2> other) {
        return Xor.create(this, other);
    }

    @Override
    default UnaryPredicate<T2> captureAlpha(T1 alpha) {
        return AlphaCapturedBinaryPredicate.create(this, alpha);
    }

    @Override
    default UnaryPredicate<T1> captureBeta(T2 beta) {
        return BetaCapturedBinaryPredicate.create(this, beta);
    }

    @Override
    default BooleanSupplier capture(T1 alpha, T2 beta) {
        return AllArgumentsCapturedBinaryPredicate.create(this, alpha, beta);
    }
}
