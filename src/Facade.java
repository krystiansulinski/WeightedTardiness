public class Facade {
    public static void main(String[] args) {
        String filePath = "/Users/krystian/Documents/studia/IX_semester/pwr/rsm/my_data/wt1.txt";
        int fileNumber = 0;
        Factory factory = new Factory();

        factory.createFile(fileNumber, filePath);
        factory.createCollection(fileNumber, 1, 3);
        System.out.println(factory.getSolution(fileNumber));
    }
}