package algorithms;

import skeleton.Instance;

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

    public BruteForceSearch(final ArrayList<Instance> dataSet) {
        solution = new HashMap<>(dataSet.size());
        run(dataSet);
    }

    private void run(final ArrayList<Instance> dataSet) {
        ArrayList<ArrayList<Integer>> indices= new ArrayList<>();
        ArrayList<Integer> totalWeightedTardinesses;

        final int numberOfJobs = dataSet.get(0).size();
        Permutations permutations = new Permutations(numberOfJobs);

        for (Instance instance : dataSet) {
            totalWeightedTardinesses = new ArrayList<>();
            for (ArrayList<Integer> jobOrder : permutations.getPermutations()) {
                Instance permutedInstance = new Instance(instance, jobOrder);

                indices.add(jobOrder);
                totalWeightedTardinesses.add(permutedInstance.solve());
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
