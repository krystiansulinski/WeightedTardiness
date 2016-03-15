import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class Facade {
    static Factory factory;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        factory = new Factory();

        int files = 2;                  // [1 - 20]
        int instances = 10;             // [1 - 10]
        int jobs = 5;                   // [1 - 6]

        int processingTimeRange = 100;   // [1, processingTimeRange], defaults to 100
        int weightRange = 10;            // [1, weightRange], defaults to 10

        factory.generateInputFiles(files, instances, jobs, processingTimeRange, weightRange);
        factory.generateOutputFilesWithBruteForceSearch(files, instances, jobs);
    }
}