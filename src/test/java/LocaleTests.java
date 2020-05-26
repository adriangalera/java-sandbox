import java.util.Locale;
import org.junit.Test;

public class LocaleTests {

    @Test
    public void testLocales() {
        System.out.println(Locale.forLanguageTag("en").toLanguageTag());
        System.out.println(Locale.forLanguageTag("en-GB").toLanguageTag());
        System.out.println(Locale.forLanguageTag("en-gb").toLanguageTag());
        System.out.println(Locale.forLanguageTag("eb_GB").toLanguageTag());
    }
}
