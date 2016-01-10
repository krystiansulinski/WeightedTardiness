import java.util.ArrayList;

/**
 * Created by krystian on 10/01/2016.
 */
public class InstancesReader {
    private FileReader fileReader;
    private ArrayList<Job> instances;
    private int instanceSize;
    private int numberOfAllInstances;

    public InstancesReader(String filePath, int instanceSize, int numberOfAllInstances) {
        fileReader = new FileReader(filePath);
        this.instanceSize = instanceSize;
        this.numberOfAllInstances = numberOfAllInstances;
        instances = new ArrayList<>(instanceSize * numberOfAllInstances);
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
            for (int i = 0; i < instanceSize; ++i) {
                instances.add(new Job(processingTimes[i], weights[i], dueTimes[i]));
            }
        }
    }

    public ArrayList<Job> getInstances() {
        return instances;
    }
}
