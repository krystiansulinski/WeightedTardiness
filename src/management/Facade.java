package management;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Facade {
    static Factory factory;
    static Facade facade;

    public static void start(int files, int instances, int jobs, int processingTimeRange, int weightRange, int numberOfRuns) throws FileNotFoundException, UnsupportedEncodingException { //public static void main(String[] args) t
        factory = new Factory();
        facade = new Facade();

        for (int i = 0; i < numberOfRuns; ++i) {
            facade.run(files, instances, jobs, processingTimeRange, weightRange);
            facade.result(files, instances, jobs);
        }
    }

    static void run(int files, int instances, int jobs, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        factory.generateInputFiles(files, instances, jobs, processingTimeRange, weightRange);
    }

    static void result(int files, int instances, int jobs) throws FileNotFoundException, UnsupportedEncodingException {
        factory.generateOutputFilesWithBruteForceSearch(files, instances, jobs);
    }

}
