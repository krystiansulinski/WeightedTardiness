package algorithms;

import java.util.Random;

/**
 * Created by krystian on 9/04/2016.
 */
public class SimulatedAnnealingUtility {
    public static double acceptanceProbability(final int currentTardiness, final int newTardiness, final double temperature) {
        if (newTardiness < currentTardiness) {
            return 1.0;
        }
        return Math.exp(currentTardiness - newTardiness)/temperature;
    }

    public static double randomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    public static int randomInt(final int min, final int max) {
        Random random = new Random();
        double number = min + random.nextDouble() * (max - min);
        return (int) number;
    }
}
