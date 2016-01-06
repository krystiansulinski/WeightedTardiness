import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by krystian on 6/01/2016.
 */
public class ManageInputFile {
    private File inputFile;
    protected Scanner textScannerOfInputFile;

    public ManageInputFile(String filePath) {
        this.inputFile = new File(filePath);
        this.textScannerOfInputFile = createScanner();
    }

    private Scanner createScanner() {
        try {
            textScannerOfInputFile =  new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textScannerOfInputFile;
    }

    protected int computeNumberOfJobs(int numberOfInstances) {
        return countIntegersInInputFile() / (3 * numberOfInstances);
    }

    private int countIntegersInInputFile() {
        int numberOfIntegersInInputFile = 0;

        while (textScannerOfInputFile.hasNextInt()) {
            textScannerOfInputFile.nextInt();
            numberOfIntegersInInputFile++;
        }
        return numberOfIntegersInInputFile;
    }


}
