package problem;

public class Job {
    private int number;
    private int processingTime;
    private int weight;
    private int dueDate;

    public Job(Job job) {
        this.number = job.getNumber();
        this.processingTime = job.getProcessingTime();
        this.weight = job.getWeight();
        this.dueDate = job.getDueDate();
    }

    public Job(final int number, final int processingTime, final int weight, final int dueDate) {
        if (processingTime < 0 || weight < 0 || dueDate < 0) {
            throw new IllegalArgumentException("Variables should be positive.");
        }
        this.number = number;
        this.processingTime = processingTime;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Job{" +
                "number=" + number +
                ", processingTime=" + processingTime +
                ", weight=" + weight +
                ", dueDate=" + dueDate +
                '}';
    }

    public String toStringRaw() {
        return Integer.toString(number);
    }

    public int getNumber() {
        return number;
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
