package skeleton;

public class Job {
    private int processingTime;
    private int weight;
    private int dueDate;

    public Job() {
    }

    public Job(final int processingTime, final int weight, final int dueDate) {
        if (processingTime < 0 || weight < 0 || dueDate < 0) {
            System.out.println("Variables should be positive.");
        }
        this.processingTime = processingTime;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getWeight() {
        return weight;
    }

    public int getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "skeleton.Job{" +
                "processingTime=" + processingTime +
                ", weight=" + weight +
                ", dueDate=" + dueDate +
                '}';
    }
}
