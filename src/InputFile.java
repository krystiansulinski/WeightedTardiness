import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class InputFile {
    private final File file;
    private Scanner scanner;
    private ArrayList<Integer> content;

    public InputFile(String filePath) {
        file = new File(filePath);
        createScanner();
    }

    private void createScanner() {
        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        content = new ArrayList<>();
        while (this.scanner.hasNextInt()) {
            content.add(this.scanner.nextInt());
        }
    }

    public ArrayList<Integer> getContent(final int firstIndex, final int lastIndex) {
        ArrayList<Integer> partOfContent = new ArrayList<>(lastIndex - firstIndex);
        for(int i = firstIndex; i < lastIndex; ++i) {
            partOfContent.add(content.get(i));
        }
        return partOfContent;
    }

    public ArrayList<Integer> getContent() {
        return content;
    }
}
