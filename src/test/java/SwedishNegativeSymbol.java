import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.junit.Test;

public class SwedishNegativeSymbol {

    DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance(Locale.forLanguageTag("se-sv"));

    public String negate(int number) {
        return "-" + number;
    }

    public String negativeNumberWorkingOnJava11(int number) {
        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance(Locale.forLanguageTag("se-sv"));
        return fmt.getNegativePrefix() + number;
    }

    @Test
    public void shouldParseNegativeNumberButFailsOnJava11() throws ParseException {
        assertEquals(-1L, fmt.parse(negate(1)));
    }

    @Test
    public void shouldParseNegativeNumber() throws ParseException {
        assertEquals(-1L, fmt.parse(negativeNumberWorkingOnJava11(1)));
    }

    @Test
    public void shouldUseSameNegativeSymbolButFailsOnJava11() {
        String expectedNegativeSymbol = fmt.getNegativePrefix();
        String negativeSymbol = negate(1).substring(0, 1);
        assertEquals("Negative symbols do not match!", expectedNegativeSymbol, negativeSymbol);
    }

    @Test
    public void printNegativeSymbols() {
        String expectedNegativeSymbol = fmt.getNegativePrefix();
        String negativeSymbol = negate(1).substring(0, 1);
        System.out.println(expectedNegativeSymbol + " vs " + negativeSymbol);
    }
}
