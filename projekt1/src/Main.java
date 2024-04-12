
public class Main {
    public static void main(String[] args) {

//        FilesExtractor filesExtractor = new FilesExtractor("C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\reuters_files", "C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\parsed_files");
//        filesExtractor.extractFiles();

        CharacteristicsExtractor characteristicsExtractor = new CharacteristicsExtractor("C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\parsed_files");
        characteristicsExtractor.extractCharacteristicsForAllArticles();
    }
}