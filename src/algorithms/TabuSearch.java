package algorithms;

import problem.Job;
import problem.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krystian on 24/05/2016.
 */
public class TabuSearch {
    private int numberOfIterations;
    private int tabuLength;

    private Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution = new HashMap<>();

    public TabuSearch(final ArrayList<Order> jobs, final int numberOfIterations, final int tabuLength) {
        this.numberOfIterations = numberOfIterations;
        this.tabuLength = tabuLength;
        run(jobs);
    }

    private void run(final ArrayList<Order> jobs) {
        ArrayList<ArrayList<Integer>> theBestOrders = new ArrayList<>();
        ArrayList<Integer> theBestTardinesses = new ArrayList<>();

        int i = 0;
        for (Order order : jobs) {
            Order solution = new Order(order);
            solution.generateRandomSolution();
            Order bestSolution = new Order(solution.getOrder());
            ArrayList<Order> tabuList = new ArrayList<>(tabuLength);

            for (int iteration = 0; iteration < numberOfIterations; ++iteration) {
                Order bestCandidate = null;

                for (Job job : bestSolution.getOrder()) {
                    Order solutionCandidate = new Order(bestSolution.getOrder());
                    solutionCandidate.swapTwoJobsRandomly();

                    if (!tabuList.contains(solutionCandidate)) {
                        if (bestCandidate == null) {
                            bestCandidate = solutionCandidate;
                        } else if (solutionCandidate.getTardiness() > bestCandidate.getTardiness()) {
                            bestCandidate = solutionCandidate;
                        }
                    }
                    solution = bestCandidate;
                    if (bestCandidate.getTardiness() > bestSolution.getTardiness()) {
                        bestSolution = bestCandidate;
                    }
                    tabuList.add(bestCandidate);
                    if (tabuList.size() > tabuLength) {
                        tabuList.remove(0);
                    }
                }
            }
            theBestTardinesses.add(bestSolution.getTardiness());
            theBestOrders.add(bestSolution.getOrderInIndexes());

            System.out.println("Tabu Search solution of Instance nr " + i++ + ": " + solution.getTardiness());
        }
        this.solution.put(theBestOrders, theBestTardinesses);
    }

    public Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> getSolution() {
        return solution;
    }
}
