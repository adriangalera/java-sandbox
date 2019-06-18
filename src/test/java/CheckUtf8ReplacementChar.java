import org.junit.Assert;
import org.junit.Test;

public class CheckUtf8ReplacementChar {


    private boolean containsUtf8ReplacementCharacter(String target) {
        final int REPLACEMENT_CHARACTER_VALUE = 65533;
        for (int i = 0; i < target.length(); i++) {
            if ((int) target.charAt(i) == REPLACEMENT_CHARACTER_VALUE) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void shouldDetectUtf8ReplacementChar() {
        final String wrongString = "Carrier bag currency-> ������������������<br>";
        final String okString = "Carrier bag currency-> <br>";
        Assert.assertTrue(containsUtf8ReplacementCharacter(wrongString));
        Assert.assertFalse(containsUtf8ReplacementCharacter(okString));
    }
}
