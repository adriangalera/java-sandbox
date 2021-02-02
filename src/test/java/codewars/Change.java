package codewars;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class Change {

    enum Coin {
        PENNIES(1, "Pennies"),
        NICKELS(5, "Nickels"),
        DIMES(10, "Dimes"),
        QUARTERS(25, "Quarters");

        int value;
        String name;

        Coin(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public static HashMap<String, Integer> looseChange(int cent) {
        if (cent <= 0) {
            return emptyChange();
        }
        return computeChangeWithGreatestCoin(cent);
    }

    public static List<Coin> sortedCoins() {
        return Stream.of(Coin.values())
            .sorted(Comparator.comparingInt(coin -> coin.value))
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
    }

    private static HashMap<String, Integer> computeChangeWithGreatestCoin(int cent) {
        HashMap<String, Integer> change = emptyChange();
        List<Coin> coins = sortedCoins();
        int pendingChange = cent;

        while (!coins.isEmpty()) {
            Coin greatestCoin = coins.remove(0);
            while (pendingChange > 0 && greatestCoin.value <= pendingChange) {
                change.put(greatestCoin.name, change.getOrDefault(greatestCoin.name, 0) + 1);
                pendingChange = pendingChange - greatestCoin.value;
            }
        }

        return change;
    }

    private static HashMap<String, Integer> emptyChange() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(Coin.PENNIES.name, 0);
        map.put(Coin.NICKELS.name, 0);
        map.put(Coin.DIMES.name, 0);
        map.put(Coin.QUARTERS.name, 0);
        return map;
    }

    @Test
    public void shouldReturnEmptyChangeWhenLowerOrEqualToZero() {
        assertEquals(emptyChange(), looseChange(-1));
        assertEquals(emptyChange(), looseChange(0));
    }

//    @Test
//    public void shouldRoundDownFloat() {
//        //loose_change(4.935) ==>  {'Nickels': 0, 'Pennies': 4, 'Dimes': 0, 'Quarters': 0}
//        Map<String, Integer> change = emptyChange();
//        change.put(Coin.PENNIES.name, 4);
//        //assertEquals(change, looseChange(4.935f));
//        fail("to be implemented");
//    }

    @Test
    public void shouldReturnLeastAmountOfCoinsInSimpleSituation() {
        HashMap<String, Integer> expectedChange = emptyChange();
        expectedChange.put(Coin.PENNIES.name, 4);
        expectedChange.put(Coin.QUARTERS.name, 1);
        assertEquals(expectedChange, Change.looseChange(29));
    }

    @Test
    public void shouldReturnLeastAmountOfCoinsInComplexSituation() {
        HashMap<String, Integer> expected = emptyChange();
        expected.put(Coin.PENNIES.name, 1);
        expected.put(Coin.NICKELS.name, 1);
        expected.put(Coin.DIMES.name, 1);
        expected.put(Coin.QUARTERS.name, 3);
        assertEquals(expected, Change.looseChange(91));
    }

    @Test
    public void shouldReturnChangeForOneCoin() {
        coinChange(1, Coin.PENNIES);
        coinChange(1, Coin.NICKELS);
        coinChange(1, Coin.DIMES);
        coinChange(1, Coin.QUARTERS);
    }

    @Test
    public void shouldReturnChangeForTwoPennies() {
        coinChange(2, Coin.PENNIES);
    }

    private void coinChange(int numberOfCoins, Coin coin) {
        HashMap<String, Integer> expected;
        expected = emptyChange();
        expected.put(coin.name, numberOfCoins);
        assertEquals(expected, Change.looseChange(numberOfCoins * coin.value));
    }
}
