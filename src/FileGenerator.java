import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by krystian on 13/03/2016.
 */
public class FileGenerator {
    private ArrayList<String> fileNames = new ArrayList<>();
    private int filesCount;
    private int instancesCount;
    private int jobsCount;

    public FileGenerator(int filesCount, int instancesCount, int jobsCount, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        this.filesCount = filesCount;
        this.instancesCount = instancesCount;
        this.jobsCount = jobsCount;
        createFiles(processingTimeRange, weightRange);
    }
    private void createFiles(int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<PrintWriter> writer = new ArrayList<>(jobsCount);

        for (int fileNumber = 0; fileNumber < filesCount; ++fileNumber) {
            fileNames.add("wt" + fileNumber + ".txt");
            writer.add(new PrintWriter(fileNames.get(fileNumber), "UTF-8"));

            for (int i = 0; i < instancesCount; ++i) {
                ArrayList<Integer> processingTimes = new ArrayList<>(jobsCount);
                ArrayList<Integer> weights = new ArrayList<>(jobsCount);
                ArrayList<Integer> dueDates = new ArrayList<>(jobsCount);

                for (int j = 0; j < jobsCount; ++j) {
                    Random random = new Random();
                    int processingTime = random.nextInt(processingTimeRange) + 1;       // uniform distribution [1, 100]
                    processingTimes.add(processingTime);

                    int weight = random.nextInt(weightRange) + 1;                       // uniform distribution [1, 10]
                    weights.add(weight);

                    int dueDate = random.nextInt(getSum(processingTimes)) + 1;          // uniform distribution [1, sum of processing times]
                    dueDates.add(dueDate);
                }
                addToFile(writer.get(fileNumber), processingTimes);
                addToFile(writer.get(fileNumber), weights);
                addToFile(writer.get(fileNumber), dueDates);
            }
            writer.get(fileNumber).close();
        }
    }

    private int getSum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum;
    }

    private void addToFile(PrintWriter writer, ArrayList<Integer> list) {
        for (Integer value : list) {
            writer.print(value + "\t");
        }
        writer.println();
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public void createOutputFile(String fileName, String content) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print(content);
        writer.close();
    }
}
