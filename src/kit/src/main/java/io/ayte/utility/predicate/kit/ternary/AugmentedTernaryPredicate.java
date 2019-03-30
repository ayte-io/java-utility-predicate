package io.ayte.utility.predicate.kit.ternary;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.delegate.And;
import io.ayte.utility.predicate.kit.ternary.delegate.Not;
import io.ayte.utility.predicate.kit.ternary.delegate.Or;
import io.ayte.utility.predicate.kit.ternary.delegate.Xor;

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
}
