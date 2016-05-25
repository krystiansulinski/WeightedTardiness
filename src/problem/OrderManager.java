package problem;

import java.util.ArrayList;

/**
 * Created by krystian on 9/04/2016.
 */
public class OrderManager {
    private static ArrayList<Job> jobs = new ArrayList<>();

    public OrderManager(ArrayList<Job> jobs) {
        for (Job job : jobs) {
            this.jobs.add(new Job(job));
        }
    }

    public static void addJob(Job job) {
        jobs.add(job);
    }

    public static Job getJob(int index) {
        return jobs.get(index);
    }

    public static int numberOfJobs() {
        return jobs.size();
    }
}
