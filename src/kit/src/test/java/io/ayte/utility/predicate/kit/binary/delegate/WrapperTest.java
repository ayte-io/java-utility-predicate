package io.ayte.utility.predicate.kit.binary.delegate;

import io.ayte.utility.predicate.BinaryPredicate;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WrapperTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> Wrapper.create(null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void passesCallsThrough() {
        val delegate = mock(BiPredicate.class);
        when(delegate.test(null, null)).thenReturn(true);
        val sut = Wrapper.create(delegate);
        assertThat(sut, instanceOf(Wrapper.class));
        assertTrue(sut.test(null, null));
        verify(delegate, times(1)).test(null, null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void doesNotWrapBinaryPredicates() {
        val delegate = mock(BinaryPredicate.class);
        val sut = Wrapper.create(delegate);
        assertThat(sut, is(delegate));
    }
}
