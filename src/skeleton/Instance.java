package skeleton;

import algorithms.Solver;

import java.util.ArrayList;

public class Instance {
    private ArrayList<Job> instance;

    public Instance(Instance instance, ArrayList<Integer> indices) {
        this.instance = new ArrayList<>(instance.size());

        for (Integer index : indices) {
            this.instance.add(instance.getJob(index));
        }
    }

    public Instance(final ArrayList<Integer> processingTimes, final ArrayList<Integer> weights, final ArrayList<Integer> dueTimes) {
        instance = new ArrayList<>(processingTimes.size());
        for (int job = 0; job < processingTimes.size(); ++job) {
            instance.add(new Job(processingTimes.get(job), weights.get(job), dueTimes.get(job)));
        }
    }

    public int solve() {
        Solver solver = new Solver(this);
        return solver.totalWeightedTardiness;
    }

    public int size() {
        return instance.size();
    }

    @Override
    public String toString() {
        return "\n" + "skeleton.Instance{" +
                "instance=" + instance +
                '}';
    }

    public Job getJob(int index) {
        return instance.get(index);
    }
}
