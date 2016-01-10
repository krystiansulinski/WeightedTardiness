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

    @Override
    public String toString() {
        return "Job{" +
                "processingTime=" + processingTime +
                ", weight=" + weight +
                ", dueDate=" + dueDate +
                '}';
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }
}
