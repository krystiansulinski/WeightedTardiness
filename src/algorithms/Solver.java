package algorithms;

import skeleton.Instance;

public class Solver {
    private Instance instance;
    public int totalWeightedTardiness;

    public Solver(Instance instance) {
        this.instance = instance;
        totalWeightedTardiness = 0;
        solve();
    }

    public void solve() {
        for (int i = 0; i < instance.size(); ++i) {
            totalWeightedTardiness += instance.getJob(i).getWeight() * tardiness(i);
        }
    }

    private int tardiness(final int index) {
        int tardiness = Math.max(completionTime(index) - instance.getJob(index).getDueDate(), 0);
        return tardiness;
    }

    private int completionTime(final int index) {
        int completionTime = 0;
        for (int i = 0; i <= index; ++i) {
            completionTime += instance.getJob(i).getProcessingTime();
        }
        return completionTime;
    }

    @Override
    public String toString() {
        return "algorithms.Solver{" +
                "instance=" + instance +
                ", totalWeightedTardiness=" + totalWeightedTardiness +
                '}';
    }
}
