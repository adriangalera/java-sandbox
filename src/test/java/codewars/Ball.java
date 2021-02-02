package codewars;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ball {

    private static final double GRAVITY = 9.81d;

    private static double toMetersSecond(double kmh) {
        return kmh * 1000d / 3600d;
    }

    public static int maxBall(int v0) {
        return computeWithDerivative(v0);
    }

    private static int computeWithDerivative(int v0) {
        //dh/dt = v - gt
        //dh/dt = 0 => max => v = gt
        //Taking into account kmh to ms conversion
        return (int) Math.round(v0 / (0.36 * GRAVITY));
    }

    private static int computeStepByStep(int v0) {
        double currentTime = 0;
        double tenthOfSecond = 0.1;
        int step = 0;
        double currentHeight;
        double nextHeight = Double.MAX_VALUE;

        while (nextHeight > 0) {
            double nextTimeStep = currentTime + tenthOfSecond;
            currentHeight = height(currentTime, v0);
            nextHeight = height(nextTimeStep, v0);
            if (nextHeight < currentHeight) {
                break;
            } else {
                currentTime = nextTimeStep;
                step++;
            }
        }
        return step;
    }

    private static double height(double time, double v0) {
        return (toMetersSecond(v0) * time) - (0.5 * GRAVITY * time * time);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests maxBall");
        testing(Ball.maxBall(37), 10);
        testing(Ball.maxBall(45), 13);
        testing(Ball.maxBall(99), 28);
        testing(Ball.maxBall(85), 24);
        testing(Ball.maxBall(15), 4);
        testing(Ball.maxBall(25), 7);
    }

    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }
}
