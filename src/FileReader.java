import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by krystian on 6/01/2016.
 */
public class FileReader {
    private File inputFile;
    Scanner textScanner;

    public FileReader(String filePath) {
        inputFile = new File(filePath);
        textScanner = createScanner();
    }

    private Scanner createScanner() {
        try {
            textScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textScanner;
    }
}
