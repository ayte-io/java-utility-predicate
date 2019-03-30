package io.ayte.utility.predicate;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * Simple {@link Predicate} extension with {@link #xor(Predicate)}
 * operation. This is auxiliary interface which may be used only if end
 * user wants to, anyone else may continue using standard
 * {@link Predicate}.
 *
 * @param <T> Argument type.
 *
 * @since 0.1.0
 */
public interface UnaryPredicate<T> extends Predicate<T> {
    /**
     * Verifies that only one of two predicates is satisfied.
     *
     * @param other Second predicate to xor with
     * @return Predicate that returns true only if one of predicates
     * (this one or provided one) returns true.
     *
     * @since 0.1.0
     */
    default UnaryPredicate<T> xor(Predicate<? super T> other) {
        return subject -> test(subject) != other.test(subject);
    }

    default BooleanSupplier capture(T subject) {
        return () -> test(subject);
    }
}
