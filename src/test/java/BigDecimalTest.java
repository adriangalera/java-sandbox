import java.math.BigDecimal;
import org.junit.Test;

public class BigDecimalTest {

    @Test
    public void shouldMapToNull() {
        String stringValue = null;
        System.out.println(new BigDecimal(stringValue));
    }
}
