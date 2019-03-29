package io.ayte.utility.predicate.kit.unary.value;

import io.ayte.utility.predicate.kit.unary.AugmentedUnaryPredicate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotEqualToInAnyCase implements AugmentedUnaryPredicate<String> {
    private final String reference;

    @Override
    public boolean test(String subject) {
        return !reference.equalsIgnoreCase(subject);
    }

    public static NotEqualToInAnyCase create(@NonNull String reference) {
        return new NotEqualToInAnyCase(reference);
    }
}
