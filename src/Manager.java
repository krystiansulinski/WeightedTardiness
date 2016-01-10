import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by krystian on 6/01/2016.
 */
public class Manager {
    public static void main(String[] args) {
        String filePath = "/Users/krystian/Documents/studia/IX_semester/pwr/rsm/my_data/wt1.txt";
        InstancesReader instancesReader = new InstancesReader(filePath, 3, 1);

        for (Job job : instancesReader.getInstances()) {
            System.out.println(job.toString());
        }
    }
}