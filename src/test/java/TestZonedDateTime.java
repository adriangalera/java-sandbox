import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.Test;

public class TestZonedDateTime {

    @Test
    public void testZonedDateTime() {
        final String creationTime = "2019-06-03T12:09:35.38Z";
        final String dateTimeFmt = "EEEE, dd/MM/yyyy HH:mm";
        ZonedDateTime zdt = ZonedDateTime.parse("2019-06-03T12:09:35.38Z");
        System.out.println(creationTime);
        DateTimeFormatter fmt = DateTimeFormatter
            .ofPattern(dateTimeFmt, Locale.forLanguageTag(Locale.UK.getLanguage()));
        String formatted = zdt.withZoneSameInstant(ZoneId.of("Europe/Madrid")).format(fmt);
        System.out.println(formatted);
    }

}
