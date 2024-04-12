
public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        FilesExtractor filesExtractor = new FilesExtractor("C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\reuters_files", "C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\parsed_files");
        filesExtractor.extractFiles();
    }
}