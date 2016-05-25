package utilities;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Facade {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String filePath = "/Users/krystian/Documents/studia/X_semester/Project_1_(IFN701)/test_files/wt1.txt";
        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> totalWeightedTardiness;
        totalWeightedTardiness = Factory.runBruteForceSearch(filePath, 2, 3);
        Factory.createOutputFile(filePath, totalWeightedTardiness, false, "BS");

//        String filePath = "/Users/krystian/Documents/studia/X_semester/Project_1_(IFN701)/test_files/wt100.txt";
//        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> totalWeightedTardiness;
//        totalWeightedTardiness = Factory.runSimulatedAnnealing(filePath, 125, 100, 100000, 0.0003);
//        Factory.createOutputFile(filePath, totalWeightedTardiness, true, "SimulatedAnnealing");

//        String filePath = "/Users/krystian/Documents/studia/X_semester/Project_1_(IFN701)/test_files/wt100.txt";
//        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> totalWeightedTardiness;
//        totalWeightedTardiness = Factory.runTabuSearch(filePath, 125, 100, 100, 10);
//        Factory.createOutputFile(filePath, totalWeightedTardiness, true, "TabuSearch");
    }
}
