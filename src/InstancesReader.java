import java.util.ArrayList;

/**
 * Created by krystian on 10/01/2016.
 */
public class InstancesReader {
    private FileReader fileReader;
    private ArrayList<Job> instances;

    public InstancesReader(String filePath) {
        fileReader = new FileReader(filePath);
        instances = new ArrayList<>();
        readInstances();
    }

    private void readInstances() {
        for (int i = 0; fileReader.textScanner.hasNextInt(); ++i) {
                instances.add(new Job(fileReader.textScanner.nextInt(),
                                  fileReader.textScanner.nextInt(),
                                  fileReader.textScanner.nextInt()));
        }
    }

    public ArrayList<Job> getInstances() {
        return instances;
    }
}
