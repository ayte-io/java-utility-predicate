package io.ayte.utility.predicate.kit.utility;

import lombok.NonNull;
import lombok.val;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class DelegateCollectionFactory<T> {
    private final Map<Predicate<T>, Function<T, Iterable<T>>> unwrappers = new HashMap<>();
    private final Map<Predicate<T>, T> breakers = new HashMap<>();
    private final Set<Predicate<T>> validators = new HashSet<>();
    private final Set<Predicate<T>> filters = new HashSet<>();
    private T fallback;
    private BiFunction<List<T>, Map<Predicate<T>, Integer>, T> collector;

    public DelegateCollectionFactory<T> withUnwrapper(Predicate<T> predicate, Function<T, Iterable<T>> unwrapper) {
        unwrappers.put(predicate, unwrapper);
        return this;
    }

    public DelegateCollectionFactory<T> withBreaker(Predicate<T> predicate, T value) {
        breakers.put(predicate, value);
        return this;
    }

    public DelegateCollectionFactory<T> withValidator(Predicate<T> predicate) {
        validators.add(predicate);
        return this;
    }

    public DelegateCollectionFactory<T> withCollector(BiFunction<List<T>, Map<Predicate<T>, Integer>, T> collector) {
        this.collector = collector;
        return this;
    }

    public DelegateCollectionFactory<T> withSimpleCollector(Function<List<T>, T> collector) {
        this.collector = (elements, any) -> collector.apply(elements);
        return this;
    }

    public DelegateCollectionFactory<T> withFallback(T fallback) {
        this.fallback = fallback;
        return this;
    }

    public DelegateCollectionFactory<T> withFilter(Predicate<T> filter) {
        this.filters.add(filter);
        return this;
    }

    public T build(Iterable<T> items) {
        val elements = unwrap(items);
        for (val element : elements) {
            for (val shortcut : breakers.entrySet()) {
                if (shortcut.getKey().test(element)) {
                    return shortcut.getValue();
                }
            }
        }
        return collect(elements);
    }

    private T collect(List<T> items) {
        switch (items.size()) {
            case 0:
                return fallback;
            case 1:
                return items.get(0);
            default:
                return collector.apply(items, validate(items));
        }
    }

    private Iterable<T> unwrap(@NonNull T item) {
        for (val entry : unwrappers.entrySet()) {
            if (entry.getKey().test(item)) {
                return entry.getValue().apply(item);
            }
        }
        return Collections.singletonList(item);
    }

    private List<T> unwrap(Iterable<T> items) {
        val target = new LinkedList<T>();
        for (val element : items) {
            for (val unwrapped : unwrap(element)) {
                if (!isFiltered(unwrapped)) {
                    target.add(unwrapped);
                }
            }
        }
        return target;
    }

    private boolean isFiltered(@NonNull T item) {
        for (val filter : filters) {
            if (filter.test(item)) {
                return true;
            }
        }
        return false;
    }

    private Map<Predicate<T>, Integer> validate(List<T> items) {
        Map<Predicate<T>, Integer> target = new HashMap<>();
        for (val item : items) {
            for (val validator : validators) {
                if (!validator.test(item)) {
                    val counter = target.getOrDefault(validator, 0);
                    target.put(validator, counter + 1);
                }
            }
        }
        return target;
    }
}