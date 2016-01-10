import java.util.ArrayList;

/**
 * Created by krystian on 10/01/2016.
 */
public class Solver {
    private ArrayList<Job> instances;
    private int totalWeightedTardiness;

    public Solver(ArrayList<Job> instances) {
        this.instances = instances;
        this.totalWeightedTardiness = 0;
        Solve();
    }

    public void Solve() {
        for (int i = 0; i < instances.size(); ++i) {
            totalWeightedTardiness += instances.get(i).getWeight() * tardiness(i);
        }
    }

    private int tardiness(int i) {
        int tardiness = Math.max(completionTime(i) - instances.get(i).getDueDate(), 0);
        return tardiness;
    }

    private int completionTime(int i) {
        int completionTime = 0;
        for (int j = 0; j <= i; ++j) {
            completionTime += instances.get(j).getProcessingTime();
        }
        return completionTime;
    }

    public int getTotalWeightedTardiness() {
        return totalWeightedTardiness;
    }

    @Override
    public String toString() {
        return "Solver{" +
                "instances=" + instances +
                ", totalWeightedTardiness=" + totalWeightedTardiness +
                '}';
    }
}
