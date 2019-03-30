package io.ayte.utility.predicate.kit.binary.delegate.collection;

import io.ayte.utility.predicate.kit.binary.delegate.Not;
import io.ayte.utility.predicate.kit.binary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.binary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.binary.standard.UsingFirst;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiPredicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class NoneOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> NoneOf.create(null));
    }

    @Test
    public void returnsTrueIfAllDelegatesReturnFalse() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(false);
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertTrue(sut.test(null, null));
        verify(delegate, times(3)).test(null, null);
    }

    @Test
    public void returnsFalseIfAnyDelegateReturnsTrue() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(false, true, false);
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null));
        verify(delegate, times(2)).test(null, null);
    }

    @Test
    public void shortcutsOnConstantTrue() {
        BiPredicate<Object, Object> delegate = (a, b) -> true;
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void ignoresConstantFalse() {
        BiPredicate<Object, Object> delegate = (a, b) -> true;
        val sut = NoneOf.create(Arrays.asList(delegate, delegate, ConstantFalse.create()));
        assertThat(sut, instanceOf(NoneOf.class));
        assertThat(((NoneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void createsConstantTrueOnEmptyDelegates() {
        val sut = NoneOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void inversesSingleDelegate() {
        val delegate = UsingFirst.create();
        val sut = NoneOf.create(Collections.singleton(delegate));
        assertThat(sut, equalTo(Not.create(delegate)));
    }

    @Test
    public void unwrapsNestedNoneOf() {
        val delegate = mock(BiPredicate.class);
        val nested = NoneOf.create(Arrays.asList(delegate, delegate));
        val sut = NoneOf.create(Arrays.asList(delegate, nested));
        assertThat(sut, instanceOf(NoneOf.class));
        assertThat(((NoneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }
}
