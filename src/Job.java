/**
 * Created by krystian on 10/01/2016.
 */
public class Job {
    private int processingTime;
    private int weight;
    private int dueDate;

    public Job(int processingTime, int weight, int dueDate) {
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
        return "Job{" +
                "processingTime=" + processingTime +
                ", weight=" + weight +
                ", dueDate=" + dueDate +
                '}';
    }
}
