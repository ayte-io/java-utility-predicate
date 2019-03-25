package io.ayte.utility.predicate.kit.unary.delegate;

import io.ayte.utility.predicate.UnaryPredicate;
import io.ayte.utility.predicate.kit.unary.standard.Identity;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
    public void wrapsRegularPredicate() {
        val delegate = mock(Predicate.class);
        when(delegate.test(any())).thenReturn(true);
        val sut = Wrapper.create(delegate);
        assertThat(sut, instanceOf(UnaryPredicate.class));
        assertTrue(sut.test(null));
        verify(delegate, times(1)).test(any());
    }

    @Test
    public void passesThroughUnaryPredicate() {
        val delegate = Identity.create();
        assertThat(Wrapper.create(delegate), is(delegate));
    }
}
