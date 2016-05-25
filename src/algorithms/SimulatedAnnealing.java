package algorithms;

import problem.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krystian on 9/04/2016.
 */
public class SimulatedAnnealing {
    private final double temperature;
    private final double coolingRate;
    private Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution = new HashMap<>();

    public SimulatedAnnealing(final ArrayList<Order> jobs, final double temperature, final double coolingRate) {
        this.temperature = temperature;
        this.coolingRate = coolingRate;
        run(jobs);
    }

    private void run(final ArrayList<Order> jobs) {
        ArrayList<ArrayList<Integer>> theBestOrders = new ArrayList<>();
        ArrayList<Integer> theBestTardinesses = new ArrayList<>();
        int i = 0;
        for (Order j : jobs) {
            double temperature = this.temperature;
            double coolingRate = this.coolingRate;

            Order currentOrder = new Order(j.getOrder());
            currentOrder.generateRandomSolution();
            Order theBestOrder = new Order(currentOrder.getOrder());

            while (temperature > 1) {
                Order newOrder = new Order(currentOrder.getOrder());
                newOrder.swapTwoJobsRandomly();

                int currentTardiness = currentOrder.getTardiness();
                int neighbourTardiness = newOrder.getTardiness();

                double random = SimulatedAnnealingUtility.randomDouble();
                if (SimulatedAnnealingUtility.acceptanceProbability(currentTardiness, neighbourTardiness, temperature) > random) {
                    currentOrder = new Order(newOrder.getOrder());
                }

                if (currentOrder.getTardiness() < theBestOrder.getTardiness()) {
                    theBestOrder = new Order(currentOrder.getOrder());
                }

                temperature *= 1 - coolingRate;
            }
            theBestTardinesses.add(theBestOrder.getTardiness());
            theBestOrders.add(theBestOrder.getOrderInIndexes());

            int solution = theBestOrder.getTardiness();

            System.out.println("Simulated Annealing solution of Instance nr " + i++ + ": " + solution);
        }
        this.solution.put(theBestOrders, theBestTardinesses);
    }

    public Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> getSolution() {
        return solution;
    }
}
