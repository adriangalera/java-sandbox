package codewars;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class SnakesLaddersTest {

    @Test
    public void shouldAlternateUsers() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 3", game.play(1, 2));
        assertEquals("Player 2 is on square 3", game.play(1, 2));
        assertEquals("Player 1 is on square 6", game.play(1, 2));
    }

    @Test
    public void shouldNotAlternateWhenDoubleDice() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 38", game.play(1, 1));
        assertEquals("Player 1 is on square 40", game.play(1, 1));
    }

    @Test
    public void shouldMoveSumOfDices() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 3", game.play(1, 2));
    }

    @Test
    public void shouldClimbLadder() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 38", game.play(1, 1));
    }

    @Test
    public void shouldSlideSnake() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 38", game.play(1, 1));
        assertEquals("Player 1 is on square 11", game.play(5, 6));
    }

    @Test
    public void playerShouldWinWhenExactHundred() {
        Map<Integer, Integer> curPos = new HashMap<>();
        curPos.put(0, 97);
        SnakesLadders game = new SnakesLadders(curPos, 0);
        assertEquals("Player 1 Wins!", game.play(1, 2));
    }

    @Test
    public void shouldBounceWhenMoreThanHundred() {
        Map<Integer, Integer> curPos = new HashMap<>();
        curPos.put(0, 97);
        SnakesLadders game = new SnakesLadders(curPos, 0);
        assertEquals("Player 1 is on square 80", game.play(1, 3));
    }

    @Test
    public void exampleTests() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 38", game.play(1, 1));
        assertEquals("Player 1 is on square 44", game.play(1, 5));
        assertEquals("Player 2 is on square 31", game.play(6, 2));
        assertEquals("Player 1 is on square 25", game.play(1, 1));
    }

    @Test
    public void game4() {
        SnakesLadders game = new SnakesLadders();
        assertEquals("Player 1 is on square 14", game.play(4, 3));
        assertEquals("Player 2 is on square 6", game.play(1, 5));
        assertEquals("Player 1 is on square 42", game.play(5, 2));
    }

    /*
    Game #4 move #1 {4, 3}: Player 1 is on square 14
Game #4 move #2 {1, 5}: Player 2 is on square 6
Game #4 move #3 {5, 2}: Player 1 is on square 21
<...ayer 1 is on square [42]> but was:<...ayer 1 is on square [21]>
     */
}
