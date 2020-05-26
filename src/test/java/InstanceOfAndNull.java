import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InstanceOfAndNull {

    @Test
    public void shouldBeAStringInstance() {
        String s = "a";
        boolean isString = s instanceof String;
        assertTrue(isString);
    }

    @Test
    public void nullShouldNotBeAStringInstance() {
        String s = null;
        boolean isString = s instanceof String;
        assertFalse(isString);
    }

    @Test
    public void integerShouldNotBeAStringInstance() {
        Object i = new Integer(2);
        boolean isString = i instanceof String;
        assertFalse(isString);
    }
}
