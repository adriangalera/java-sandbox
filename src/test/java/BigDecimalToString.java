import java.math.BigDecimal;

public class BigDecimalToString {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(23.123);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());


        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(bigDecimal.toPlainString());
    }
}
