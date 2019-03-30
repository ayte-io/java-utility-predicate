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
class AllOfTest {
    @Test
    public void rejectsNullPredicates() {
        assertThrows(NullPointerException.class, () -> AllOf.create(null));
    }

    @Test
    public void returnsTrueIfAllDelegatesMatch() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(true);
        val sut = AllOf.create(Arrays.asList(delegate, delegate));
        assertThat(sut, instanceOf(AllOf.class));
        assertTrue(sut.test(null, null));
        verify(delegate, times(2)).test(null, null);
    }

    @Test
    public void returnsFalseIfAnyDelegateFails() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(false);
        val sut = AllOf.create(Arrays.asList(delegate, delegate));
        assertThat(sut, instanceOf(AllOf.class));
        assertFalse(sut.test(null, null));
        verify(delegate, times(1)).test(null, null);
    }

    @Test
    public void createsConstantTrueOnEmptyDelegates() {
        val sut = AllOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void ignoresConstantTrueInDelegates() {
        val delegate = mock(BiPredicate.class);
        val sut = AllOf.create(Arrays.asList(delegate, delegate, ConstantTrue.create()));
        assertThat(sut, instanceOf(AllOf.class));
        assertThat(((AllOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortcutsOnConstantFalse() {
        val delegate = mock(BiPredicate.class);
        val sut = AllOf.create(Arrays.asList(delegate, delegate, ConstantFalse.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void passesSingleDelegateThrough() {
        val delegate = UsingFirst.create();
        val sut = AllOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }

    @Test
    public void unwrapsNestedAllOf() {
        val delegate = mock(BiPredicate.class);
        val nested = AllOf.create(Arrays.asList(delegate, delegate));
        val sut = AllOf.create(Arrays.asList(delegate, nested));
        assertThat(sut, instanceOf(AllOf.class));
        assertThat(((AllOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }
}
