package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.kit.unary.standard.ConstantFalse;
import io.ayte.utility.predicate.kit.unary.standard.ConstantTrue;
import io.ayte.utility.predicate.kit.unary.standard.Identity;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AnyOfTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> AnyOf.create(null));
    }

    @Test
    public void returnsConstantTrueIfAnyOfDelegatesIsConstantTrue() {
        val sut = AnyOf.create(Arrays.asList(any -> fail(), ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantTrue.class));
    }

    @Test
    public void returnsConstantFalseOnEmptyDelegates() {
        val sut = AnyOf.create(Collections.emptyList());
        assertThat(sut, instanceOf(ConstantFalse.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void returnsTrueIfAnyOfDelegatesReturnsTrue() {
        val alpha = mock(Predicate.class);
        when(alpha.test(any())).thenReturn(false);
        val beta = mock(Predicate.class);
        when(beta.test(any())).thenReturn(true);
        val gamma = mock(Predicate.class);
        when(gamma.test(any())).thenReturn(false);
        val sut = AnyOf.create(Arrays.asList(alpha, beta, gamma));
        assertTrue(sut.test(null));
        verify(alpha, times(1)).test(null);
        verify(beta, times(1)).test(null);
        verify(gamma, never()).test(null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void returnsFalseIfAllDelegatesReturnFalse() {
        val alpha = mock(Predicate.class);
        when(alpha.test(any())).thenReturn(false);
        val beta = mock(Predicate.class);
        when(beta.test(any())).thenReturn(false);
        val sut = AnyOf.create(Arrays.asList(alpha, beta));
        assertFalse(sut.test(null));
        verify(alpha, times(1)).test(null);
        verify(beta, times(1)).test(null);
    }

    @Test
    public void unwrapsNestedAnyOf() {
        Predicate<Object> delegate = any -> true;
        val alpha = AnyOf.create(Arrays.asList(delegate, ConstantFalse.create(), delegate));
        val beta = AnyOf.create(Arrays.asList(delegate, alpha));
        assertThat(beta, instanceOf(AnyOf.class));
        assertThat(((AnyOf) beta).getDelegates(), equalTo(Arrays.asList(delegate, delegate, delegate)));
    }

    @Test
    public void shortcutsOnSingleDelegate() {
        val delegate = Identity.create();
        val sut = AnyOf.create(Collections.singleton(delegate));
        assertThat(sut, is(delegate));
    }

    @Test
    public void stripsConstantFalse() {
        val delegate = Identity.create();
        val sut = AnyOf.create(Arrays.asList(delegate, delegate, ConstantFalse.<Boolean>create()));
        assertThat(sut, instanceOf(AnyOf.class));
        assertThat(((AnyOf) sut).getDelegates(), equalTo(Arrays.asList(delegate, delegate)));
    }

    @Test
    public void shortcutsOnConstantTrue() {
        val sut = AnyOf.create(Arrays.asList(any -> true, ConstantTrue.create()));
        assertThat(sut, instanceOf(ConstantTrue.class));
    }
}
