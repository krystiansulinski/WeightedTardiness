import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class Factory {
    private FileGenerator fileGenerator;
    private ArrayList<InputFile> file;
    private ArrayList<Instances> instances;
    private ArrayList<Instances> bunchOfInstancesForBruteForceSearch;

    public Factory() {
        file = new ArrayList<>(1);
        instances = new ArrayList<>();
    }

    public void createFile(final int fileNumber, final String filePath) {
        InputFile file = new InputFile(filePath);
        file.readFile();
        this.file.add(fileNumber, file);
    }

    public boolean createInstances(final int fileNumber, final int instanceSize, final int jobSize) {
        Instances instances = new Instances();
        for (int instance = 0; instance < instanceSize; ++instance) {
            int firstIndex = instance * jobSize * 3;

            ArrayList<Integer> processingTimes = getData(fileNumber, jobSize, firstIndex);
            ArrayList<Integer> weights = getData(fileNumber, jobSize, firstIndex + jobSize );
            ArrayList<Integer> dueDates = getData(fileNumber, jobSize, firstIndex + jobSize * 2);

            Instance jobs = new Instance(createInstance(processingTimes, weights, dueDates));
            instances.add(jobs);
        }

        this.instances.add(instances);
        return true;
    }

    private ArrayList<Integer> getData(final int fileNumber, final int jobSize, final int firstIndex) {
        ArrayList<Integer> data = new ArrayList<>(jobSize);
        final int lastIndex = firstIndex + jobSize;

        ArrayList<Integer> content = file.get(fileNumber).getContent(firstIndex, lastIndex);
        for (Integer job : content) {
            data.add(job);
        }
        return data;
    }

    private ArrayList<Job> createInstance(final ArrayList<Integer> processingTimes, final ArrayList<Integer> weights, final ArrayList<Integer> dueTimes) {
        ArrayList<Job> instance = new ArrayList<>(processingTimes.size());
        for (int job = 0; job < processingTimes.size(); ++job) {
            instance.add(new Job(processingTimes.get(job), weights.get(job), dueTimes.get(job)));
        }
        return instance;
    }

    private Job createJob(final int processingTime, final int weight, final int dueTime) {
        return new Job(processingTime, weight, dueTime);
    }

    public void bruteForceSearch(int fileNumber) {
        Instances instances = this.instances.get(fileNumber);
        bunchOfInstancesForBruteForceSearch = new ArrayList<>();

        Permutations permutations = new Permutations();
        permutations.permute(instances.jobSze());
        ArrayList<ArrayList<Integer>> list = permutations.getPermutations();

        for (int k = 0; k < instances.size(); ++k) {
            Instances allInstances = new Instances();
            for (int i = 0; i < list.size(); ++i) {
                Instance instance = new Instance();
                for (int j = 0; j < list.get(i).size(); ++j) {
                    Instance instanceBFC = new Instance();
                    Job jobBFC = new Job();

                    instanceBFC = instances.get(k);
                    int permutedIndex = list.get(i).get(j);

                    jobBFC = instanceBFC.get(permutedIndex);

                    instance.add(jobBFC);
                }
                allInstances.add(instance);
            }
            bunchOfInstancesForBruteForceSearch.add(allInstances);
        }
    }

    public String getSolution(final int fileNumber) {
        Instances instances = this.instances.get(fileNumber);
        String toString = "";

        for (int i = 0; i < instances.size(); ++i) {
            Solver solver = new Solver(instances.get(i));
            solver.solve();
            toString += "\n" + solver.toString();
        }

        return toString;
    }

    public void generateInputFiles(int fileCount, int instancesCount, int jobsCount, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        fileGenerator = new FileGenerator(fileCount, instancesCount, jobsCount, processingTimeRange, weightRange);
    }

    public ArrayList<String> getGeneratedFileNames() {
        return fileGenerator.getFileNames();
    }

    public ArrayList<String> getGeneratedFileNamesWithoutExtension() {
        ArrayList<String> fileNames  = fileGenerator.getFileNames();
        ArrayList<String> fileNamesWithoutExtension = new ArrayList<>();
        for (String fileName : fileNames) {
            fileNamesWithoutExtension.add(fileName.replace(".txt", ""));

        }
        return fileNamesWithoutExtension;
    }

    public void createOutputFile(String fileName, String extendedFileName, String content) throws FileNotFoundException {
        String newFileName = fileName + extendedFileName;
        fileGenerator.createOutputFile(newFileName, content);
    }

    public String getSolutionOfBruteForceSearch() {
        String toString = "";
        int count = 1;
        for (int k = 0; k < bunchOfInstancesForBruteForceSearch.size(); ++k) {
            for (int i = 0; i < bunchOfInstancesForBruteForceSearch.get(k).size(); ++i) {
                Solver solver = new Solver(bunchOfInstancesForBruteForceSearch.get(k).get(i));
                solver.solve();
                toString += "\n" + "(" + count++ + ") " + solver.toString();
            }
        }
        return toString;
    }

    public ArrayList<Integer> getFile(final int fileNumber) {
        return file.get(fileNumber).getContent();
    }

    public void generateOutputFilesWithBruteForceSearch(int files, int instancesCount, int jobsCount) throws FileNotFoundException {
        ArrayList<String> fileNames = getGeneratedFileNames();
        ArrayList<String> fileNamesWithoutExtension = getGeneratedFileNamesWithoutExtension();

        for (int i = 0; i < files; ++i) {
                createFile(i, fileNames.get(i));
                createInstances(i, instancesCount, jobsCount);
                bruteForceSearch(i);
                createOutputFile(fileNamesWithoutExtension.get(i), "_bruteForceSearch.txt", getSolutionOfBruteForceSearch());
            }
    }
}
