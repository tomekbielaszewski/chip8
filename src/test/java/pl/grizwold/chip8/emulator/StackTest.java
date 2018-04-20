package pl.grizwold.chip8.emulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StackTest {
    private Stack stack = new Stack();

    @Test
    public void shouldInitializeZeroedStack() throws Exception {
        assertThat(stack.pop(), is((short) 0x000));
        assertThat(stack.pop(), is((short) 0x000));
        assertThat(stack.pop(), is((short) 0x000));
        assertThat(stack.pop(), is((short) 0x000));
        assertThat(stack.pop(), is((short) 0x000)); //and one out of max capacity
    }

    @Test
    public void shouldPopSameValueAsPushed() throws Exception {
        short pushedValue = 0xeee;
        stack.push(pushedValue);

        short topOnStack = stack.pop();

        assertThat(topOnStack, is(pushedValue));
    }

    @Test
    public void shouldPopSameValuesAsPushed() throws Exception {
        short pushedValue1 = 0x111;
        short pushedValue2 = 0x222;
        short pushedValue3 = 0x333;
        short pushedValue4 = 0x444;
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
    public void shouldNotOverwriteLastValueWhenExceededCapacity() throws Exception {
        short lastValuePushedWhenInCapacity = -1;
        for (int i = 0; i < 16; i++) {
            lastValuePushedWhenInCapacity = (short) i;
            stack.push((short) i);
        }

        short valueWhenExceededCapacity = 1234;
        stack.push(valueWhenExceededCapacity);

        short topOnStack = stack.pop();

        assertTrue(topOnStack == lastValuePushedWhenInCapacity);
        assertTrue(topOnStack != valueWhenExceededCapacity);
    }
}