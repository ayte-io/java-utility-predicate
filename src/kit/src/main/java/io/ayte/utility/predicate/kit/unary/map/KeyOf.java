package io.ayte.utility.predicate.kit.unary.map;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyOf<K> implements AugmentedUnaryPredicate<K> {
    private final Map<? super K, ?> pool;

    @Override
    public boolean test(K subject) {
        return pool.containsKey(subject);
    }

    public static <K> UnaryPredicate<K> create(@NonNull Map<? super K, ?> pool) {
        return new KeyOf<>(pool);
    }
}
