package io.ayte.utility.predicate.kit.ternary.delegate;

import io.ayte.utility.predicate.TernaryPredicate;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.ternary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.ternary.standard.UsingFirst;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class AllOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> AllOf.create(null));
    }

    @Test
    public void returnsConstantTrueOnEmptyDelegates() {
        val sut = AllOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void returnsSingleDelegate() {
        val delegate = UsingFirst.create();
        val sut = AllOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }

    @Test
    public void returnsTrueIfAllDelegatesReturnTrue() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(any(), any(), any())).thenReturn(true);
        val sut = AllOf.create(Arrays.asList(delegate, delegate, delegate));
        assertTrue(sut.test(null, null, null));
        verify(delegate, times(3)).test(null, null, null);
    }

    @Test
    public void returnsFalseIfAnyDelegateReturnsFalse() {
        val delegate = mock(TernaryPredicate.class);
        when(delegate.test(any(), any(), any())).thenReturn(true, false, true);
        val sut = AllOf.create(Arrays.asList(delegate, delegate, delegate));
        assertFalse(sut.test(null, null, null));
        verify(delegate, times(2)).test(null, null, null);
    }

    @Test
    public void stripsConstantTrue() {
        val delegate = mock(TernaryPredicate.class);
        val sut = AllOf.create(Arrays.asList(delegate, ConstantTrue.create(), delegate));
        assertThat(sut, instanceOf(AllOf.class));
        assertThat(((AllOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortCircuitsOnConstantFalse() {
        val sut = AllOf.create(Arrays.asList((a, b, c) -> true, ConstantFalse.create()));
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @Test
    public void unwrapsNestedAllOf() {
        val delegate = mock(TernaryPredicate.class);
        val nested = AllOf.create(Arrays.asList(delegate, delegate));
        val sut = AllOf.create(Arrays.asList(delegate, nested));
        assertThat(sut, instanceOf(AllOf.class));
        assertThat(((AllOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }
}
