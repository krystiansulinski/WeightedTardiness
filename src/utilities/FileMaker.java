package utilities;

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

    public FileMaker(final int jobssCount, final int jobsCount, final int processingTimeRange, final int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        makeInputFile(jobssCount, jobsCount, processingTimeRange, weightRange);
    }

    public FileMaker(final String filepath, final Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content, final boolean onlyTardiness) throws FileNotFoundException, UnsupportedEncodingException {
        makeOutputFile(filepath, content, onlyTardiness);
    }

    private void makeInputFile(final int jobsCount, final int jobCount, final int processingTimeRange, final int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        String filename = getInputFilename(jobsCount, jobCount);
        this.filepath = System.getProperty("user.dir") + "/" + filename;
        PrintWriter printWriter = new PrintWriter(filename, "UTF-8");

        for (int i = 0; i < jobsCount; ++i) {
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

    private void makeOutputFile(final String filepath, final Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> content, final boolean onlyTardiness) throws FileNotFoundException, UnsupportedEncodingException {
        this.filepath = filepath;
        String filename =  this.filepath.substring( this.filepath.lastIndexOf("/" ) + 1);
        PrintWriter printWriter = new PrintWriter(filename, "UTF-8");

        for (Map.Entry<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> entry: content.entrySet()) {
            int i = 0;
            if (!onlyTardiness) {
                do {
                    printWriter.print(entry.getKey().get(i) + " = ");
                    printWriter.print(entry.getValue().get(i));
                    ++i;
                    if (i != entry.getKey().size()) {
                        printWriter.print("\n");
                    }
                } while (i < entry.getKey().size());
            }
            else
            {
                do {
                    printWriter.print(entry.getValue().get(i));
                    ++i;
                    if (i != entry.getKey().size()) {
                        printWriter.print("\n");
                    }
                } while (i < entry.getKey().size());
            }
            break;
        }

        printWriter.close();
    }

    private int getSum(final ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum;
    }

    public String getInputFilename(final int jobsCount, final int jobCount) {
        return "wt_" + jobsCount + "x" + jobCount + ".txt";
    }

    public String getFilepath() {
        return filepath;
    }
}
