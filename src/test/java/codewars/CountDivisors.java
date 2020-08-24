package codewars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountDivisors {

    private static CountDivisors fd = new CountDivisors();

    public long numberOfDivisors(int n) {
        int divisors = 0;
        for (int i = 1; i < n + 1; i++) {
            if (n % i == 0) {
                divisors++;
            }
        }
        return divisors;
    }

    @Test
    public void fourTest() {
        assertEquals("Sould return 3 if the parameter equals 4", 3, fd.numberOfDivisors(4));
    }

    @Test
    public void fiveTest() {
        assertEquals("Sould return 2 if the parameter equals 5", 2, fd.numberOfDivisors(5));
    }

    @Test
    public void twelveTest() {
        assertEquals("Sould return 6 if the parameter equals 12", 6, fd.numberOfDivisors(12));
    }

    @Test
    public void thirtyTest() {
        assertEquals("Sould return 8 if the parameter equals 30", 8, fd.numberOfDivisors(30));
    }

    @Test
    public void zeroTest() {
        assertEquals("zero", 0, fd.numberOfDivisors(0));
    }
}
