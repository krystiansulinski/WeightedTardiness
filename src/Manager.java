import java.util.ArrayList;

/**
 * Created by krystian on 6/01/2016.
 */
public class Manager {
    public static void main(String[] args) {
        String filePath = "/Users/krystian/Documents/studia/IX_semester/pwr/rsm/my_data/wt1.txt";
        InstancesReader instancesReader = new InstancesReader(filePath, 3, 1);
        ArrayList<ArrayList<Job>> instances = instancesReader.getInstances();

        for (ArrayList<Job> job : instances) {
            for(int i = 0; i < 1; ++i) {
                Solver solver = new Solver(job);
                System.out.println(solver.toString());
            }
        }
    }
}