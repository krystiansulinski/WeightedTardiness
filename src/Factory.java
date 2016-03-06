import java.util.ArrayList;

class Factory {
    private ArrayList<InputFile> files;
    private ArrayList<Instances> collections;

    public Factory() {
        files = new ArrayList<>(3);
        collections = new ArrayList<>(3);
    }

    public boolean createFile(final int fileNumber, final String filePath) {
        files.add(fileNumber, new InputFile(filePath));
        return true;
    }

    public ArrayList<Integer> getFile(final int fileNumber) {
        return files.get(fileNumber).getContent();
    }

    public boolean createCollection(final int fileNumber, final int instanceSize, final int jobSize) {
        collections.add(fileNumber, new Instances(new ArrayList<>(instanceSize)));

        for (int instance = 0; instance < instanceSize; ++instance) {
            int firstIndex = instance * jobSize;
            ArrayList<Integer> processingTimes = getData(fileNumber, jobSize, firstIndex);
            ArrayList<Integer> weights = getData(fileNumber, jobSize, firstIndex + jobSize );
            ArrayList<Integer> dueDates = getData(fileNumber, jobSize, firstIndex + jobSize * 2);

            collections.get(fileNumber).add(createInstance(processingTimes, weights, dueDates));
        }
        return true;
    }

    public String getSolution(final int fileNumber) {
        Solver solver = new Solver(collections.get(fileNumber));
        solver.solve(fileNumber);
        return solver.toString();
    }

    private ArrayList<Job> createInstance(final ArrayList<Integer> processingTimes, final ArrayList<Integer> weights, final ArrayList<Integer> dueTimes) {
        ArrayList<Job> jobs = new ArrayList<>(processingTimes.size());
        for (int job = 0; job < processingTimes.size(); ++job) {
            jobs.add(new Job(processingTimes.get(job), weights.get(job), dueTimes.get(job)));
        }
        return jobs;
    }

    private ArrayList<Integer> getData(final int fileNumber, final int jobSize, final int firstIndex) {
        ArrayList<Integer> data = new ArrayList<>(jobSize);
        final int lastIndex = firstIndex + jobSize;

        ArrayList<Integer> content = files.get(fileNumber).getContent(firstIndex, lastIndex);
        for (int job = 0; job < jobSize; ++job) {
            data.add(content.get(job));
        }
        return data;
    }
}
