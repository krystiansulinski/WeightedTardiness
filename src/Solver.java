class Solver {
    private Instances instances;
    private int totalWeightedTardiness = 0;

    public Solver(Instances instances) {
        this.instances = instances;
    }

    public void solve(final int fileNumber) {
        for (int i = 0; i < instances.get(fileNumber).size(); ++i) {
            totalWeightedTardiness += instances.get(fileNumber).get(i).getWeight() * tardiness(fileNumber, i);
        }
    }

    private int tardiness(final int fileNumber, final int index) {
        return Math.max(completionTime(fileNumber, index) - instances.get(fileNumber).get(index).getDueDate(), 0);
    }

    private int completionTime(final int fileNumber, final int index) {
        int completionTime = 0;
        for (int i = 0; i <= index; ++i) {
            completionTime += instances.get(fileNumber).get(i).getProcessingTime();
        }
        return completionTime;
    }

    public int getTotalWeightedTardiness() {
        return totalWeightedTardiness;
    }

    @Override
    public String toString() {
        return "Solver{" +
                "instances=" + instances +
                ", totalWeightedTardiness=" + totalWeightedTardiness +
                '}';
    }
}
