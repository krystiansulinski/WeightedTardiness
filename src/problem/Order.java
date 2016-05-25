package problem;

import algorithms.SimulatedAnnealingUtility;
import utilities.TotalWeightedTardiness;

import java.util.ArrayList;
import java.util.Collections;

public class Order {
    private ArrayList<Job> jobs;
    private int totalWeightedTardiness = 0;

    public Order() {
        for (int i = 0; i < OrderManager.numberOfJobs(); ++i) {
            jobs.add(null);
        }
    }

    public Order(ArrayList<Job> jobs) {
        this.jobs = (ArrayList<Job>) jobs.clone();
    }

    public Order(Order jobs) {
        this.jobs = new ArrayList<>(jobs.jobsSize());

        for (Job job : jobs.getOrder()) {
            this.jobs.add(job);
        }
    }

    public Order(Order jobs, ArrayList<Integer> indices) {
        this.jobs = new ArrayList<>(jobs.jobsSize());

        for (Integer index : indices) {
            this.jobs.add(jobs.getJob(index));
        }
    }

    public Order(final ArrayList<Integer> processingTimes, final ArrayList<Integer> weights, final ArrayList<Integer> dueTimes) {
        jobs = new ArrayList<>(processingTimes.size());
        for (int jobNumber = 0; jobNumber < processingTimes.size(); ++jobNumber) {
            jobs.add(new Job(jobNumber, processingTimes.get(jobNumber), weights.get(jobNumber), dueTimes.get(jobNumber)));
        }
    }

    public void generateRandomSolution() {
        for (int jobIndex = 0; jobIndex < OrderManager.numberOfJobs(); ++jobIndex) {
            setJob(jobIndex, OrderManager.getJob(jobIndex));
        }
        Collections.shuffle(jobs);
    }

    public int getTardiness() {
        totalWeightedTardiness = new TotalWeightedTardiness(this).getSolution();
        return totalWeightedTardiness;
    }

    public int jobsSize() {
        return jobs.size();
    }

    @Override
    public String toString() {
        return "Order{" +
                "jobs=" + jobs +
                ", totalWeightedTardiness=" + totalWeightedTardiness +
                '}';
    }

    public String toStringRaw() {
        String string = jobs.get(0).toStringRaw();
        for (int i = 1; i < jobs.size(); ++i) {
            string += ", " + jobs.get(i).toStringRaw();
        }
        return string;
    }

    public Job getJob(int index) {
        return jobs.get(index);
    }

    public void setJob(int index, Job job) {
        jobs.set(index, job);
    }

    public ArrayList<Job> getOrder() {
        return this.jobs;
    }

    public ArrayList<Integer> getOrderInIndexes() {
        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 0; i < jobs.size(); ++i) {
            order.add(jobs.get(i).getNumber());
        }
        return order;
    }

    public void swapTwoJobsRandomly() {
        int randomJobPosition1;
        int randomJobPosition2;

        do {
            randomJobPosition1 = SimulatedAnnealingUtility.randomInt(0, jobsSize());
            randomJobPosition2 = SimulatedAnnealingUtility.randomInt(0, jobsSize());
        } while (randomJobPosition1 == randomJobPosition2);

        Job jobToSwap1 = getJob(randomJobPosition1);
        Job jobToSwap2 = getJob(randomJobPosition2);

        setJob(randomJobPosition2, jobToSwap1);
        setJob(randomJobPosition1, jobToSwap2);
    }
}
