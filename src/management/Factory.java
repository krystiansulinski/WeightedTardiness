package management;

import algorithms.BruteForceSearch;
import algorithms.Solver;
import skeleton.Instance;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Factory {
    public static Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> runBruteForceSearch(final String filePath, final int instanceSize, final int numberOfJobs) {
        ArrayList<Instance> instances = createInstances(filePath, instanceSize, numberOfJobs);
        BruteForceSearch bruteForceSearch = new BruteForceSearch(instances);
        return bruteForceSearch.getSolution();
    }

    public static String createInputFile(int instanceCount, int jobCount, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        FileMaker fileMaker = new FileMaker(instanceCount, jobCount, processingTimeRange, weightRange);
        return fileMaker.getFilepath();
    }

    public static String createOutputFile(final String inputFilepath,  Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content) throws FileNotFoundException, UnsupportedEncodingException {
        String outputFilepath = inputFilepath.replace(".txt", "_out.txt");
        FileMaker fileMaker = new FileMaker(outputFilepath, content);
        return fileMaker.getFilepath();
    }

    public static String getInputFileName(final int instanceCount, final int jobCount) {
        FileMaker fileMaker = new FileMaker();
        return fileMaker.getInputFilename(instanceCount, jobCount);
    }
    private static Map<Instance, Integer> solve(ArrayList<Instance> instances) {
        Map<Instance, Integer> solution = new HashMap<>();
        ArrayList<Integer> totalWeigtedTardinesses = new ArrayList<>(instances.size());

        for (int i = 0; i < instances.size(); ++i) {
            Solver solver = new Solver(instances.get(i));
            solver.solve();
            solution.put(instances.get(i), solver.totalWeightedTardiness);
        }
        return solution;
    }

    private static ArrayList<Instance> createInstances(final String filePath, final int instanceSize, final int numberOfJobs) {
        ArrayList<Instance> instances = new ArrayList<>();
        InputFile file = loadFile(filePath);

        final int jobSize = numberOfJobs;
        for (int instance = 0; instance < instanceSize; ++instance) {
            int firstIndex = instance * jobSize * 3;
            instances.add(new Instance(
                    file.getContent(firstIndex, firstIndex + jobSize),
                    file.getContent(firstIndex + jobSize, firstIndex + jobSize * 2),
                    file.getContent(firstIndex + jobSize * 2, firstIndex + jobSize * 3)));
        }
        return instances;
    }

    private static InputFile loadFile(final String filePath) {
        return new InputFile(filePath);
    }
}
