package io.ayte.utility.predicate.kit.binary.delegate.collection;

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
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class OneOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> OneOf.create(null));
    }

    @Test
    public void returnsFalseIfNoPredicatesMatch() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(false, false, false);
        val sut = OneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null));
        verify(delegate, times(3)).test(null, null);
    }

    @Test
    public void returnsTrueIfSingleDelegateMatches() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(false, true, false);
        val sut = OneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertTrue(sut.test(null, null));
        verify(delegate, times(3)).test(null, null);
    }

    @Test
    public void returnsFalseIfMultiplePredicatesMatch() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(true);
        val sut = OneOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null));
        verify(delegate, times(2)).test(null, null);
    }

    @Test
    public void ignoresConstantFalse() {
        val delegate = mock(BiPredicate.class);
        val sut = OneOf.create(Arrays.asList(delegate, delegate, ConstantFalse.create()));
        assertThat(sut, instanceOf(OneOf.class));
        assertThat(((OneOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortcutsMultipleConstantTrue() {
        val sut = OneOf.create(Arrays.asList(ConstantTrue.create(), ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void passesSingleDelegateThrough() {
        val delegate = UsingFirst.create();
        assertThat(OneOf.create(Collections.singleton(delegate)), is(delegate));
    }

    @Test
    public void returnsConstantFalseOnEmptyDelegates() {
        val sut = OneOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }
}
