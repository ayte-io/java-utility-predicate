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

/**
 * This class helps aggregate predicates to process input collection of
 * delegate predicates, filtering out irrelevant ones (ConstantTrue's
 * may be safely dropped for all-predicates-have-to-match condition,
 * since we know they'll match in advance), unwrapping others (nobody
 * likes excessive nesting, so why not to flatten things out?) or
 * short-circuiting on specific conditions (e.g. if all predicates match
 * and ConstantFalse is present, then it's known in advance that
 * aggregate predicate won't match).
 *
 * @param <T> Source type.
 * @param <V> Target type.
 *
 * @since 0.1.0
 */
public class DelegateCollectionFactory<T, V> {
    /**
     * List of flatteners responsible of unwrappping nested predicates
     * into lists of previously-wrapped predicates.
     */
    private final Map<Predicate<T>, Function<T, Iterable<T>>> flatteners = new HashMap<>();
    /**
     * Breakers which halt the pipeline if condition has been met. In
     * such case map value will be returned.
     */
    private final Map<Predicate<T>, V> breakers = new HashMap<>();
    /**
     * Run over each element and save the count of violations (i.e.
     * {@code false}-returning checks). This, for example, allows to
     * get number of ConstantTrue's in OneOf and thus return
     * ConstantFalse if there were several.
     */
    private final Set<Predicate<T>> validators = new HashSet<>();
    /**
     * Filters force factory to completely ignore specific predicates.
     */
    private final Set<Predicate<T>> filters = new HashSet<>();
    /**
     * Value to return if no predicates are left after all operations
     */
    private V fallback;
    /**
     * Function that converts resulting delegate list into single
     * predicate.
     */
    private BiFunction<List<T>, Map<Predicate<T>, Integer>, V> collector;
    /**
     * Function that converts single predicate (if resulting list
     * consists of single delegate) into target type.
     */
    private Function<T, V> wrapper;

    public DelegateCollectionFactory<T, V> withFlattener(Predicate<T> predicate, Function<T, Iterable<T>> flattener) {
        flatteners.put(predicate, flattener);
        return this;
    }

    public DelegateCollectionFactory<T, V> withBreaker(Predicate<T> predicate, V value) {
        breakers.put(predicate, value);
        return this;
    }

    public DelegateCollectionFactory<T, V> withValidator(Predicate<T> predicate) {
        validators.add(predicate);
        return this;
    }

    public DelegateCollectionFactory<T, V> withCollector(BiFunction<List<T>, Map<Predicate<T>, Integer>, V> collector) {
        this.collector = collector;
        return this;
    }

    public DelegateCollectionFactory<T, V> withSimpleCollector(Function<List<T>, V> collector) {
        this.collector = (elements, any) -> collector.apply(elements);
        return this;
    }

    public DelegateCollectionFactory<T, V> withFallback(V fallback) {
        this.fallback = fallback;
        return this;
    }

    public DelegateCollectionFactory<T, V> withFilter(Predicate<T> filter) {
        this.filters.add(filter);
        return this;
    }

    public DelegateCollectionFactory<T, V> withWrapper(Function<T, V> wrapper) {
        this.wrapper = wrapper;
        return this;
    }

    public V build(Iterable<T> items) {
        val elements = flatten(items);
        for (val element : elements) {
            for (val shortcut : breakers.entrySet()) {
                if (shortcut.getKey().test(element)) {
                    return shortcut.getValue();
                }
            }
        }
        return collect(elements);
    }

    private V collect(List<T> items) {
        switch (items.size()) {
            case 0:
                return fallback;
            case 1:
                return wrapper.apply(items.get(0));
            default:
                return collector.apply(items, validate(items));
        }
    }

    private Iterable<T> flatten(@NonNull T item) {
        for (val entry : flatteners.entrySet()) {
            if (entry.getKey().test(item)) {
                return entry.getValue().apply(item);
            }
        }
        return Collections.singletonList(item);
    }

    private List<T> flatten(Iterable<T> items) {
        val target = new LinkedList<T>();
        for (val element : items) {
            for (val unwrapped : flatten(element)) {
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
