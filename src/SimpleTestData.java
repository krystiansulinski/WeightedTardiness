/**
 * Created by krystian on 6/01/2016.
 */
public class SimpleTestData extends ManageInputFile {
    private int[][] processingTime;
    private int[][] weights;
    private int[][] dueDates;

    private int numberOfInstances;
    private int numberOfJobs;

    public SimpleTestData(String filePath, int numberOfInstances) {
        super(filePath);

        checkIfNumberOfInstancesEqualsZero(numberOfInstances);
        this.numberOfInstances = numberOfInstances;
        this.numberOfJobs = computeNumberOfJobs(this.numberOfInstances);

        createParameters();
        setParameters();
    }

    private void checkIfNumberOfInstancesEqualsZero(int numberOfInstances) {
        if (numberOfInstances == 0) {
            throw new NullPointerException();
        }
    }

    private void createParameters() {
        this.processingTime = new int[numberOfInstances][numberOfJobs];
        this.weights = new int[numberOfInstances][numberOfJobs];
        this.dueDates = new int[numberOfInstances][numberOfJobs];
    }

    private void setParameters() {
        int numberOfInstance = 0;
        while (numberOfInstance++ < this.numberOfInstances) {
            setProcessingTime(numberOfInstance);
            setWeights(numberOfInstance);
            setDueDates(numberOfInstance);
        }
    }

    private void setProcessingTime(int numberOfInstance) {
        for (int jobNumber = 0; jobNumber < this.numberOfJobs; jobNumber++) {
            processingTime[numberOfInstance][jobNumber] = textScannerOfInputFile.nextInt();
        }
    }

    private void setWeights(int numberOfInstance) {
        for (int jobNumber = 0; jobNumber < this.numberOfJobs; jobNumber++) {
            weights[numberOfInstance][jobNumber] = textScannerOfInputFile.nextInt();
        }
    }

    private void setDueDates(int numberOfInstance) {
        for (int jobNumber = 0; jobNumber < this.numberOfJobs; jobNumber++) {
            dueDates[numberOfInstance][jobNumber] = textScannerOfInputFile.nextInt();
        }
    }
}