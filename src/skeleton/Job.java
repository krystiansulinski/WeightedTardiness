package skeleton;

public class Job {
    private int processingTime;
    private int weight;
    private int dueDate;

    public Job(final Job job) {
        this.processingTime = job.getProcessingTime();
        this.weight = job.getWeight();
        this.dueDate = job.getDueDate();
    }

    public Job(final int processingTime, final int weight, final int dueDate) {
        if (processingTime < 0 || weight < 0 || dueDate < 0) {
            throw new IllegalArgumentException("Variables should be positive.");
        }
        this.processingTime = processingTime;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "skeleton.Job{" +
                "processingTime=" + processingTime +
                ", weight=" + weight +
                ", dueDate=" + dueDate +
                '}';
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
}
