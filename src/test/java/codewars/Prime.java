package codewars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Prime {

//    public static List<Integer> computePrimeNumbersUntil(int num) {
//
//        List<Integer> primeNumbers = new ArrayList();
//
//        for (int primeCandidate = 2; primeCandidate < num; primeCandidate++) {
//            boolean isPrime = false;
//            double squareRoot = Math.sqrt(primeCandidate);
//            if (!isWhole(squareRoot)) {
//                int maxPrimeToCheck = new Double(Math.floor(squareRoot)).intValue();
//                //Iterate only on the primes lower than the square root
//                List<Integer> previousPrimes = primeNumbers.stream().filter(prime -> prime <= maxPrimeToCheck).collect(
//                    Collectors.toList());
//                boolean isDivisible = false;
//                for (Integer prime : previousPrimes) {
//                    isDivisible = primeCandidate % prime == 0;
//                    if (isDivisible) {
//                        break;
//                    }
//                }
//                if (!isDivisible) {
//                    isPrime = true;
//                }
//            }
//            if (isPrime) {
//                primeNumbers.add(primeCandidate);
//            }
//        }
//        return primeNumbers;
//    }

    public static boolean isPrime(int num) {

        if (num <= 1) {
            return false;
        }

        double squareRoot = Math.sqrt(num);
        if (isWhole(squareRoot)) {
            return false;
        } else {
            int maxPrimeToCheck = new Double(Math.floor(squareRoot)).intValue();
//            List<Integer> primeNumbers = computePrimeNumbersUntil(maxPrimeToCheck);
//            if (primeNumbers.isEmpty()) {
//                primeNumbers = computePrimeNumbersUntil(num);
//            }

            boolean isPrime = true;
            for (int candidate = 2; candidate < maxPrimeToCheck + 1; candidate++) {
                if (num % candidate == 0) {
                    isPrime = false;
                }
            }
            return isPrime;
        }
    }

    private static boolean isWhole(double squareRoot) {
        return squareRoot % 1 == 0;
    }

//    @Test
//    public void testGeneratePrimes() {
//        List<Integer> primes = codewars.Prime.computePrimeNumbersUntil(20);
//        assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19), primes);
//    }

    @Test
    public void testBasic() {
        assertFalse("0  is not prime", Prime.isPrime(0));
        assertFalse("1  is not prime", Prime.isPrime(1));
        assertTrue("2  is prime", Prime.isPrime(2));
        assertTrue("73 is prime", Prime.isPrime(73));
        assertFalse("75 is not prime", Prime.isPrime(75));
        assertFalse("-1 is not prime", Prime.isPrime(-1));
    }

    @Test
    public void testPrime() {
        assertTrue("3 is prime", Prime.isPrime(3));
        assertTrue("5 is prime", Prime.isPrime(5));
        assertTrue("7 is prime", Prime.isPrime(7));
        assertTrue("41 is prime", Prime.isPrime(41));
        assertTrue("5099 is prime", Prime.isPrime(5099));
        assertTrue("5099 is prime", Prime.isPrime(5099));
    }

    @Test
    public void testNotPrime() {
        //assertFalse("4 is not prime", codewars.Prime.isPrime(4));
        assertFalse("6 is not prime", Prime.isPrime(6));
        assertFalse("8 is not prime", Prime.isPrime(8));
        assertFalse("9 is not prime", Prime.isPrime(9));
        assertFalse("45 is not prime", Prime.isPrime(45));
        assertFalse("-5 is not prime", Prime.isPrime(-5));
        assertFalse("-8 is not prime", Prime.isPrime(-8));
        assertFalse("-41 is not prime", Prime.isPrime(-41));
    }

    @Test
    public void testBigNumberIsNotPrime() {
        assertFalse("1112422609 is not prime", Prime.isPrime(1112422609));
    }

}
