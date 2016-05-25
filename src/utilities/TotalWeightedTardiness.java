package utilities;

import problem.Order;

/**
 * Created by krystian on 9/04/2016.
 */
public class TotalWeightedTardiness {
    private Order jobs;
    private int solution;

    public TotalWeightedTardiness(Order jobs) {
        this.jobs = jobs;
        this.solution = 0;
        solve();
    }

    private void solve() {
        for (int i = 0; i < jobs.jobsSize(); ++i) {
            solution += jobs.getJob(i).getWeight() * tardiness(i);
        }
    }

    private int tardiness(final int index) {
        int tardiness = Math.max(completionTime(index) - jobs.getJob(index).getDueDate(), 0);
        return tardiness;
    }

    private int completionTime(final int index) {
        int completionTime = 0;
        for (int i = 0; i <= index; ++i) {
            completionTime += jobs.getJob(i).getProcessingTime();
        }
        return completionTime;
    }

    public int getSolution() {
        return solution;
    }

    @Override
    public String toString() {
        return "utilities.Factory.TotalWeightedTardiness{" +
                "jobs=" + jobs +
                ", solution=" + solution +
                '}';
    }
}
