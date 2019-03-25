package io.ayte.utility.predicate;

import java.util.function.BooleanSupplier;

/**
 * Standard library didn't have support for three argumented
 * predicates, so I've brought it.
 *
 * Of course using such predicate is a bad smell, but from time to time
 * we just urgently need such thing.
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 *
 * @since 0.1.0
 */
public interface TernaryPredicate<T1, T2, T3> {
    boolean test(T1 alpha, T2 beta, T3 gamma);

    /**
     * Creates a conjunction of two predicates.
     *
     * @since 0.1.0
     */
    default TernaryPredicate<T1, T2, T3> and(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return (alpha, beta, gamma) -> this.test(alpha, beta, gamma) && other.test(alpha, beta, gamma);
    }

    /**
     * Creates disjunction of two predicates.
     *
     * @since 0.1.0
     */
    default TernaryPredicate<T1, T2, T3> or(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return (alpha, beta, gamma) -> this.test(alpha, beta, gamma) || other.test(alpha, beta, gamma);
    }

    default TernaryPredicate<T1, T2, T3> negate() {
        return (alpha, beta, gamma) -> !this.test(alpha, beta, gamma);
    }

    /**
     * Verifies that only one of two predicates is satisfied.
     */
    default TernaryPredicate<T1, T2, T3> xor(TernaryPredicate<? super T1, ? super T2, ? super T3> other) {
        return ((alpha, beta, gamma) -> this.test(alpha, beta, gamma) != other.test(alpha, beta, gamma));
    }

    default BinaryPredicate<T2, T3> captureAlpha(T1 alpha) {
        return (beta, gamma) -> test(alpha, beta, gamma);
    }

    default BinaryPredicate<T1, T3> captureBeta(T2 beta) {
        return (alpha, gamma) -> test(alpha, beta, gamma);
    }

    default BinaryPredicate<T1, T2> captureGamma(T3 gamma) {
        return (alpha, beta) -> test(alpha, beta, gamma);
    }

    default BinaryPredicate<T2, T3> capture(T1 alpha) {
        return captureAlpha(alpha);
    }

    default UnaryPredicate<T3> capture(T1 alpha, T2 beta) {
        return gamma -> test(alpha, beta, gamma);
    }

    default BooleanSupplier capture(T1 alpha, T2 beta, T3 gamma) {
        return () ->test(alpha, beta, gamma);
    }
}
