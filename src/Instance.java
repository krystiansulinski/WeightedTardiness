import java.util.ArrayList;

class Instance {
    private ArrayList<Job> instance;

    public Instance() {
        instance = new ArrayList<Job>();
    }

    public Instance(ArrayList<Job> instance) {
        this.instance = instance;
    }

    public boolean add(Job job) {
        return instance.add(job);
    }

    public int size() {
        return instance.size();
    }

    public Job get(int index) {
        return instance.get(index);
    }

    public Job getJob(int index) {
        return instance.get(index);
    }

    public ArrayList<Job> getInstance() {
        return instance;

    }

    @Override
    public String toString() {
        return "Instance{" +
                "instance=" + instance +
                '}';
    }

}
