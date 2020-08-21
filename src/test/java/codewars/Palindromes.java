package codewars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Palindromes {

    public static int longestPalindrome(final String s) {
        final String withoutSpaces = s.replaceAll(" ", "");
        String reverse = reverse(withoutSpaces);
        int longestPalindromeSize = 0;

        String candidate = "";

        for (char c : s.toCharArray()) {
            candidate = candidate + c;
            if (reverse.contains(candidate)) {
                //System.out.println("Palindrome detected ::: " + candidate);
                if (candidate.equals(reverse(candidate))) {
                    longestPalindromeSize = Math.max(candidate.length(), longestPalindromeSize);
                }
            } else {
                candidate = Character.toString(c);
            }
        }

        return longestPalindromeSize;
    }

    private static String reverse(String withoutSpaces) {
        StringBuilder sb = new StringBuilder(withoutSpaces);
        sb.reverse();
        return sb.toString();
    }

    @Test
    public void basicTests() {
        doTest("", 0);
        doTest("a", 1);
        doTest("aa", 2);
        doTest("baa", 2);
        doTest("aab", 2);
        doTest("zyabyz", 1);
        doTest("baabcd", 4);
        doTest("abcacba", 7);

        doTest("baablkj12345432133d", 9);
        doTest("I like racecars that go fast", 7);
        doTest("b3tspypp48.1ifv 4.pp f9qwko5 vdj aoa jdv 5okwq9f pp.4 vfi1.84ppyps0", 63);
    }

    private void doTest(final String s, int expected) {
        assertEquals(expected, Palindromes.longestPalindrome(s));
    }
}
