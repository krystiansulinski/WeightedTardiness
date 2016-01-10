import java.util.ArrayList;

/**
 * Created by krystian on 10/01/2016.
 */
public class InstancesReader {
    private FileReader fileReader;
    private int instanceSize;
    private int numberOfAllInstances;
    private ArrayList<ArrayList<Job>> instances;

    public InstancesReader(String filePath, int instanceSize, int numberOfAllInstances) {
        fileReader = new FileReader(filePath);
        this.instanceSize = instanceSize;
        this.numberOfAllInstances = numberOfAllInstances;
        instances = new ArrayList<>(numberOfAllInstances);
        readInstances();
    }

    private void readInstances() {
        int processingTimes[] = new int[instanceSize];
        int weights[] = new int[instanceSize];
        int dueTimes[] = new int[instanceSize];

        for (int j = 0; j < numberOfAllInstances; ++j){
            for (int i = 0; fileReader.textScanner.hasNextInt() && i < instanceSize; ++i) {
                processingTimes[i] = fileReader.textScanner.nextInt();
            }
            for (int i = 0; fileReader.textScanner.hasNextInt() && i < instanceSize; ++i) {
                weights[i] = fileReader.textScanner.nextInt();
            }

            for (int i = 0; fileReader.textScanner.hasNextInt() && i < instanceSize; ++i) {
                dueTimes[i] = fileReader.textScanner.nextInt();
            }

            instances.add(new ArrayList<Job>(instanceSize));
            for (int i = 0; i < instanceSize; ++i) {
                instances.get(j).add(new Job(processingTimes[i], weights[i], dueTimes[i]));
            }
        }
    }

    public ArrayList<ArrayList<Job>> getInstances() {
        return instances;
    }
}
