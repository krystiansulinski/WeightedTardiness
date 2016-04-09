package management;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by krystian on 13/03/2016.
 */
public class FileMaker {
    private String filepath;

    public FileMaker() {

    }

    public FileMaker(final int instancesCount, final int jobsCount, final int processingTimeRange, final int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        makeInputFile(instancesCount, jobsCount, processingTimeRange, weightRange);
    }

    public FileMaker(final String filepath, final Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content) throws FileNotFoundException, UnsupportedEncodingException {
        makeOutputFile(filepath, content);
    }

    private void makeInputFile(final int instanceCount, final int jobCount, final int processingTimeRange, final int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        String filename = getInputFilename(instanceCount, jobCount);
        this.filepath = System.getProperty("user.dir") + "/" + filename;
        PrintWriter printWriter = new PrintWriter(filename, "UTF-8");

        for (int i = 0; i < instanceCount; ++i) {
            ArrayList<Integer> processingTimes = new ArrayList<>(jobCount);
            Random random = new Random();

            for (int j = 0; j < jobCount; ++j) {
                // uniform distribution [1, processingTimeRange]
                processingTimes.add((random.nextInt(processingTimeRange) + 1));
                printWriter.print(processingTimes.get(j) + "\t");
            }
            printWriter.println();

            for (int j = 0; j < jobCount; ++j) {
                // uniform distribution [1, weightRange]
                printWriter.print((random.nextInt(weightRange) + 1) + "\t");
            }
            printWriter.println();

            for (int j = 0; j < jobCount; ++j) {
                // uniform distribution [1, sum of processing times]
                printWriter.print(random.nextInt(getSum(processingTimes) + 1) + "\t");
            }
            printWriter.println();
        }
        printWriter.close();
    }

    private void makeOutputFile(final String filepath, final Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content) throws FileNotFoundException, UnsupportedEncodingException {
        this.filepath = filepath;
        String filename =  this.filepath.substring( this.filepath.lastIndexOf("/" ) + 1);
        PrintWriter printWriter = new PrintWriter(filename, "UTF-8");
        printWriter.print(content.toString());
        printWriter.close();
    }

    private int getSum(final ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum;
    }

    public String getInputFilename(final int instanceCount, final int jobCount) {
        return "wt_" + instanceCount + "x" + jobCount + ".txt";
    }

    public String getFilepath() {
        return filepath;
    }
}
