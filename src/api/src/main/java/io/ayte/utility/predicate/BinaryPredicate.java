package io.ayte.utility.predicate;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;

/**
 * {@link BiPredicate} extensions solely for support of
 * {@link #xor(BiPredicate)} operation.
 *
 * @param <T1> First argument type.
 * @param <T2> Second argument type.
 *
 * @since 0.1.0
 */
public interface BinaryPredicate<T1, T2> extends BiPredicate<T1, T2> {
    /**
     * @param other Other predicate.
     * @return Predicate that returns true only if one of predicates
     * (this one or provided one) returns true.
     *
     * @since 0.1.0
     */
    default BinaryPredicate<T1, T2> xor(BiPredicate<? super T1, ? super T2> other) {
        return (alpha, beta) -> test(alpha, beta) != other.test(alpha, beta);
    }

    default UnaryPredicate<T2> captureAlpha(T1 alpha) {
        return beta -> test(alpha, beta);
    }

    default UnaryPredicate<T1> captureBeta(T2 beta) {
        return alpha -> test(alpha, beta);
    }

    default UnaryPredicate<T2> capture(T1 alpha) {
        return captureAlpha(alpha);
    }

    default BooleanSupplier capture(T1 alpha, T2 beta) {
        return () -> test(alpha, beta);
    }
}
