package codewars;

import java.util.HashMap;
import java.util.Map;

public class SnakesLadders {

    private int currentPlayer = 0;
    private final int numUsers = 2;
    private Map<Integer, Integer> positions = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private boolean gameOver = false;

    public SnakesLadders() {
        setupGame();
    }

    public SnakesLadders(Map<Integer, Integer> positions, int currentPlayer) {
        setupGame();
        this.positions = positions;
        this.currentPlayer = currentPlayer;
    }

    private void setupGame() {
        positions.put(0, 0);
        positions.put(1, 0);

        ladders.put(2, 38);
        ladders.put(7, 14);
        ladders.put(8, 31);
        ladders.put(15, 26);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(78, 98);
        ladders.put(87, 94);

        snakes.put(99, 80);
        snakes.put(95, 75);
        snakes.put(92, 88);
        snakes.put(89, 68);
        snakes.put(74, 53);
        snakes.put(64, 60);
        snakes.put(62, 19);
        snakes.put(49, 11);
        snakes.put(46, 25);
        snakes.put(16, 6);
    }

    public String play(int die1, int die2) {
        if (gameOver) {
            return "Game over!";
        }
        int newPosition = positions.get(currentPlayer) + die1 + die2;
        move(newPosition);
        if (newPosition == 100) {
            gameOver = true;
            return String.format("Player %s Wins!", currentPlayer + 1);
        }
        if (newPosition > 100) {
            int bounce = newPosition - 100;
            move(100 - bounce);
        }
        String msg = String.format("Player %s is on square %s", currentPlayer + 1, positions.get(currentPlayer));
        alternateUser(die1, die2);
        return msg;
    }

    private void move(int newPosition) {
        if (isLadderStart(newPosition)) {
            newPosition = ladders.get(newPosition);
        }
        if (isSnakeHead(newPosition)) {
            newPosition = snakes.get(newPosition);
        }
        positions.put(currentPlayer, newPosition);
    }

    private boolean isSnakeHead(int position) {
        return snakes.containsKey(position);
    }

    private boolean isLadderStart(int position) {
        return ladders.containsKey(position);
    }

    private void alternateUser(int die1, int die2) {
        if (die1 != die2) {
            currentPlayer = (currentPlayer + 1) % numUsers;
        }
    }
}
