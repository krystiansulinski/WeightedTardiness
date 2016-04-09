package management;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Facade {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String filePath;
        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> totalWeightedTardinesses;

        filePath = Factory.createInputFile(3, 3, 100, 20);
        totalWeightedTardinesses = Factory.runBruteForceSearch(filePath, 1, 3);

        System.out.println(totalWeightedTardinesses.toString());
        System.out.println(Factory.createOutputFile(filePath, totalWeightedTardinesses));
    }
}
