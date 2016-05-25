package utilities;

import algorithms.BruteForceSearch;
import algorithms.SimulatedAnnealing;
import algorithms.TabuSearch;
import problem.Order;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public abstract class Factory {
    public static Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> runBruteForceSearch(final String filePath, final int jobsSize, final int numberOfJobs) {
        ArrayList<Order> jobs = createOrders(filePath, jobsSize, numberOfJobs);
        BruteForceSearch bruteForceSearch = new BruteForceSearch(jobs);
        return bruteForceSearch.getSolution();
    }

    public static Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> runSimulatedAnnealing(final String filePath, final int jobsSize, final int numberOfJobs, final double temperature, final double coolingRate) {
        ArrayList<Order> jobs = createOrders(filePath, jobsSize, numberOfJobs);
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(jobs, temperature, coolingRate);
        return simulatedAnnealing.getSolution();
    }

    public static Map<ArrayList<ArrayList<Integer>>,ArrayList<Integer>> runTabuSearch(final String filePath, final int jobsSize, final int numberOfJobs, final int numberOfIterations, final int tabuLength) {
        ArrayList<Order> jobs = createOrders(filePath, jobsSize, numberOfJobs);
        TabuSearch tabuSearch = new TabuSearch(jobs, numberOfIterations, tabuLength);
        return tabuSearch.getSolution();
    }

    public static int solve(Order jobs) {
        return jobs.getTardiness();
    }

    public static String createInputFile(int jobsCount, int jobCount, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        FileMaker fileMaker = new FileMaker(jobsCount, jobCount, processingTimeRange, weightRange);
        return fileMaker.getFilepath();
    }

    public static String createOutputFile(final String inputFilepath, final Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content, final boolean onlyTardiness, final String sufix) throws FileNotFoundException, UnsupportedEncodingException {
        String outputFilepath = inputFilepath.replace(".txt", sufix + "_out.txt");
        FileMaker fileMaker = new FileMaker(outputFilepath, content, onlyTardiness);
        return fileMaker.getFilepath();
    }

    private static ArrayList<Order> createOrders(final String filePath, final int jobsSize, final int numberOfJobs) {
        ArrayList<Order> jobs = new ArrayList<>();
        InputFile file = loadFile(filePath);

        final int jobSize = numberOfJobs;
        for (int job = 0; job < jobsSize; ++job) {
            int firstIndex = job * jobSize * 3;
            jobs.add(new Order(
                    file.getContent(firstIndex, firstIndex + jobSize),
                    file.getContent(firstIndex + jobSize, firstIndex + jobSize * 2),
                    file.getContent(firstIndex + jobSize * 2, firstIndex + jobSize * 3)));
        }
        return jobs;
    }

    private static InputFile loadFile(final String filePath) {
        return new InputFile(filePath);
    }
}
