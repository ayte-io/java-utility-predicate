package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.function.Predicate;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Not<T> implements AugmentedUnaryPredicate<T> {
    private final Predicate<T> delegate;

    public Predicate<T> getDelegate() {
        return delegate;
    }

    @Override
    public boolean test(T subject) {
        return !delegate.test(subject);
    }

    public static <T> UnaryPredicate<T> create(@NonNull Predicate<T> subject) {
        if (!(subject instanceof Not)) {
            return new Not<>(subject);
        }
        val delegate = ((Not<T>) subject).getDelegate();
        return Wrapper.create(delegate);
    }
}
