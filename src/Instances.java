import java.util.ArrayList;

public class Instances {
    private ArrayList<ArrayList<Job>> instances;

    public Instances(ArrayList<ArrayList<Job>> instances) {
        this.instances = instances;
    }

    public boolean add(ArrayList<Job> instance) {
        return instances.add(instance);
    }

    public ArrayList<Job> get(int index) {
        return instances.get(index);
    }

    public int size() {
        return instances.size();
    }

    public int size(int index) {
        return instances.get(index).size();
    }

    public Job getJob(int indexOfInstance, int indexOfJob) {
        return instances.get(indexOfInstance).get(indexOfJob);
    }

    public ArrayList<ArrayList<Job>> getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        return "Instances{" +
                "instances=" + instances +
                '}';
    }
}
