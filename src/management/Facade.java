package management;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Facade {
    static Factory factory;
    static Facade facade;

    static void start() throws FileNotFoundException, UnsupportedEncodingException { //public static void main(String[] args) t
        factory = new Factory();
        facade = new Facade();

        int files = 4;                      // [1 - 20]
        int instances = 5;                  // [1 - 10]
        int jobs = 5;                       // [1 - 6]
        int processingTimeRange = 100;      // [1, processingTimeRange], defaults to 100
        int weightRange = 10;               // [1, weightRange], defaults to 10
        int numberOfRuns = 1;

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
