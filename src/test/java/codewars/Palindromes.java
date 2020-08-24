package codewars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Palindromes {

//    public static int longestPalindrome(final String s) {
//        final String withoutSpaces = s.replaceAll(" ", "");
//        String reverse = reverse(withoutSpaces);
//        int longestPalindromeSize = 0;
//
//        String candidate = "";
//
//        for (char c : s.toCharArray()) {
//            candidate = candidate + c;
//            System.out.println(candidate);
//            if (reverse.contains(candidate)) {
//                if (candidate.equals(reverse(candidate))) {
//                    System.out.println("Palindrome detected ::: " + candidate);
//                    longestPalindromeSize = Math.max(candidate.length(), longestPalindromeSize);
//                }
//            } else {
//                candidate = Character.toString(c);
//                System.out.println(c);
//                if (reverse.contains(candidate)) {
//                    if (candidate.equals(reverse(candidate))) {
//                        System.out.println("Palindrome detected ::: " + candidate);
//                        longestPalindromeSize = Math.max(candidate.length(), longestPalindromeSize);
//                    }
//                } else {
//                    candidate = "";
//                }
//            }
//        }
//
//        return longestPalindromeSize;
//    }

    public static int longestPalindrome(final String s) {
        int longestPalindromeSize = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String candidate = s.substring(i, j + 1);
                if (candidate.equals(reverse(candidate))) {
                    longestPalindromeSize = Math.max(candidate.length(), longestPalindromeSize);
                }

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
        doTest("zyabyz", 1);
        doTest("baabcd", 4);
        doTest("abcacba", 7);

        doTest("baablkj12345432133d", 9);
        doTest("I like racecars that go fast", 7);
    }

    @Test
    public void moreTests() {
        doTest("zzbaabcd", 4);

        doTest("b3tspypp48.1ifv 4.pp f9qwko5 vdj aoa jdv 5okwq9f pp.4 vfi1.84ppyps0", 63);
    }

    private void doTest(final String s, int expected) {
        assertEquals(expected, Palindromes.longestPalindrome(s));
    }
}
