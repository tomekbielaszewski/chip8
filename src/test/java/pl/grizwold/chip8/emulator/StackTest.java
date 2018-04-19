package pl.grizwold.chip8.emulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class StackTest {
    private Stack stack = new Stack();

    @Test
    public void shouldInitializeZeroedStack() throws Exception {
        assertThat(stack.pop(), is((short) 0x00));
        assertThat(stack.pop(), is((short) 0x00));
        assertThat(stack.pop(), is((short) 0x00));
        assertThat(stack.pop(), is((short) 0x00));
        assertThat(stack.pop(), is((short) 0x00)); //and one out of max capacity
    }

    @Test
    public void shouldPopSameValueAsPushed() throws Exception {
        short pushedValue = 0xff;
        stack.push(pushedValue);

        short topOnStack = stack.pop();
        short secondOnStack = stack.pop();

        assertThat(topOnStack, is(pushedValue));
        assertThat(secondOnStack, is((short) 0x00));
    }

    @Test
    public void shouldPopSameValuesAsPushed() throws Exception {
        short pushedValue1 = 0x11;
        short pushedValue2 = 0x22;
        short pushedValue3 = 0x33;
        short pushedValue4 = 0x44;
        stack.push(pushedValue1);
        stack.push(pushedValue2);
        stack.push(pushedValue3);
        stack.push(pushedValue4);

        short top = stack.pop();
        short second = stack.pop();
        short third = stack.pop();
        short fourth = stack.pop();

        assertThat(top, is(pushedValue4));
        assertThat(second, is(pushedValue3));
        assertThat(third, is(pushedValue2));
        assertThat(fourth, is(pushedValue1));
    }

    @Test
    public void shouldForgetFirstValueWhenExceededCapacity() throws Exception {
        short firstValue = 0x66;
        short otherValue = 0xaa;
        stack.push(firstValue);
        stack.push(otherValue);
        stack.push(otherValue);
        stack.push(otherValue);
        stack.push(otherValue);

        short topOnStack = stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        short outOfStackCapacity = stack.pop();

        assertThat(topOnStack, is(otherValue));
        assertThat(outOfStackCapacity, not(is(firstValue)));
        assertThat(outOfStackCapacity, is((short) 0x00));
    }
}