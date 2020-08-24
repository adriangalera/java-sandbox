package codewars;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class SplitStrings {

    public static String[] solution(String s) {
        final String PADDING_CHAR = "_";
        String word = s;
        if (s.length() % 2 != 0) {
            word = word + PADDING_CHAR;
        }
        List<String> pairs = new ArrayList<>();
        Matcher matcher = Pattern.compile(".{1,2}").matcher(word);

        while (matcher.find()) {
            pairs.add(matcher.group());
        }

        return pairs.toArray(new String[0]);
    }

    @Test
    public void testEvenString() {
        String s = "abcdef";
        String s1 = "HelloWorld";
        assertEquals("Should handle even string", "[ab, cd, ef]", Arrays.toString(SplitStrings.solution(s)));
        assertEquals("Should handle even string", "[He, ll, oW, or, ld]", Arrays.toString(SplitStrings.solution(s1)));
    }

    @Test
    public void testOddString() {
        String s = "abcde";
        String s1 = "LovePizza";
        assertEquals("Should handle odd string", "[ab, cd, e_]", Arrays.toString(SplitStrings.solution(s)));
        assertEquals("Should handle odd string", "[Lo, ve, Pi, zz, a_]", Arrays.toString(SplitStrings.solution(s1)));
    }

    @Test
    public void testSpaceString() {
        String s1 = "Hello World";
        assertEquals("Should handle Space in string", "[He, ll, o , Wo, rl, d_]",
                     Arrays.toString(SplitStrings.solution(s1)));
    }
}
