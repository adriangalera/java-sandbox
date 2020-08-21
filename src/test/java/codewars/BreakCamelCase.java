package codewars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BreakCamelCase {

    public static String camelCase(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            final char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                builder.append(" ");
            }
            builder.append(currentChar);
        }
        return builder.toString();
    }

    @Test
    public void tests() {
        assertEquals("Incorrect", "camel Casing", camelCase("camelCasing"));
        assertEquals("Incorrect", "camel Casing Test", camelCase("camelCasingTest"));
        assertEquals("Incorrect", "camelcasingtest", camelCase("camelcasingtest"));
    }
}
