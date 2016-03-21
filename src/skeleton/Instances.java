package skeleton;

import java.util.ArrayList;

public class Instances {
    private ArrayList<Instance> instances;

    public Instances() {
        instances = new ArrayList<>();
    }

    public Instances(int size) {
        instances = new ArrayList<>(size);
    }

    public int size() {
        return instances.size();
    }

    public int jobSze() {
        return instances.get(0).size();
    }

    public boolean add(Instance instance) {
        return instances.add(instance);
    }

    public void set(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    public Instance get(int index) {
        return instances.get(index);
    }

    public ArrayList<Instance> get() {
        return instances;
    }

    @Override
    public String toString() {
        return "skeleton.Instances{" +
                "instances=" + instances +
                '}';
    }
}
