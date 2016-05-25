package algorithms;

import problem.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krystian on 26/03/2016.
 */
public class BruteForceSearch {
    private Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution;
    private long size;
    private long elapsedTime;

    public BruteForceSearch(final ArrayList<Order> dataSet) {
        solution = new HashMap<>(dataSet.size());
        run(dataSet);
    }

    private void run(final ArrayList<Order> dataSet) {
        ArrayList<ArrayList<Integer>> indices;
        ArrayList<Integer> totalWeightedTardinesses;

        final int numberOfJobs = dataSet.get(0).jobsSize();
        Permutations permutations = new Permutations(numberOfJobs);

        int i = 0;
        for (Order jobs : dataSet) {
            indices = new ArrayList<>();
            totalWeightedTardinesses = new ArrayList<>();
            for (ArrayList<Integer> jobOrder : permutations.getPermutations()) {
                Order permutedOrder = new Order(jobs, jobOrder);

                indices.add(jobOrder);
                totalWeightedTardinesses.add(permutedOrder.getTardiness());
            }
            solution.put(indices, totalWeightedTardinesses);
        }
        size = solution.size();
        elapsedTime = permutations.getElapsedTime();
    }

    public Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> getSolution() {
        return solution;
    }

    public long getSize() {
        return size;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
