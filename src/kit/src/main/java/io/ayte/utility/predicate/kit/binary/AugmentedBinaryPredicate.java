package io.ayte.utility.predicate.kit.binary;

import io.ayte.utility.predicate.BinaryPredicate;
import io.ayte.utility.predicate.kit.binary.delegate.And;
import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.delegate.Or;
import io.ayte.utility.predicate.kit.binary.delegate.Xor;

import java.util.function.BiPredicate;

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
}
