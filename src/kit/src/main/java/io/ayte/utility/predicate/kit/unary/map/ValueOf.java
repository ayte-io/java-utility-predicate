package io.ayte.utility.predicate.kit.unary.map;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueOf<V> implements AugmentedUnaryPredicate<V> {
    private final Map<?, ? super V> pool;

    @Override
    public boolean test(V subject) {
        return pool.containsValue(subject);
    }

    public static <V> UnaryPredicate<V> create(@NonNull Map<?, ? super V> pool) {
        return new ValueOf<>(pool);
    }
}
