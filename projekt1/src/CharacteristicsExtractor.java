import enums.C9_dic;
import enums.c10_dic;

import java.io.*;
import java.util.EnumSet;

public class CharacteristicsExtractor {

    String articlesDirectoryPath;

    public CharacteristicsExtractor(String articlesDirectoryPath) {
        this.articlesDirectoryPath = articlesDirectoryPath;
    }


    public String getArticlesDirectoryPath() {
        return articlesDirectoryPath;
    }

    public void extractCharacteristicsForAllArticles() {
        File directory = new File(articlesDirectoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Specified path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                extractCharacteristicsFromArticle(file.getAbsolutePath(), file.getName());
            }
        }
    }

    private void extractCharacteristicsFromArticle(String absolutePath, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            String articleDateline = "";
            StringBuilder articleBody = new StringBuilder();
            String articleTitle = "";
            int lineCounter = 0;

            while ((line = reader.readLine()) != null) {
                if (lineCounter == 1) {
                    articleTitle = line;
                } else if (lineCounter == 2) {
                    articleDateline = line;
                } else if (lineCounter > 2) {
                    articleBody.append(line).append("\n");
                }
                lineCounter++;
            }
            System.out.println("filename: " + filename);
            String c7 = extractC7(articleDateline);

            String c8 = extractC8(articleDateline);

            Boolean c9 = extractC9(articleBody.toString(), C9_dic.values());

            String c10 = extractC10(articleBody.toString(), c10_dic.values());

            StringBuilder characteristics = new StringBuilder();
            characteristics.append("\n").append(c7).append("\n").append(c8).append("\n").append(c9).append("\n").append(c10);
            appendCharacteristicsToFile(absolutePath ,characteristics.toString());

//            System.out.println("Characteristics extracted and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String extractC7(String articleDateline) {
        if (articleDateline.contains(",")) {
            return articleDateline.substring(0, articleDateline.indexOf(",")).trim();
        } else {
            return articleDateline.trim();
        }
    }

    private String extractC8(String articleDateline) {
        if (articleDateline.contains("-") && articleDateline.contains(",")) {
            return articleDateline.substring(articleDateline.lastIndexOf(",") + 1, articleDateline.lastIndexOf("-")).trim();
        } else {
            return "";
        }
    }

    private Boolean extractC9(String articleBody, C9_dic[] c9_dictonary) {
        for (C9_dic c9 : c9_dictonary) {
            if (articleBody.contains(c9.getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    private String extractC10(String articleBody, c10_dic[] c10_dictonary) {
        for (c10_dic c10 : c10_dictonary) {
            if (articleBody.contains(c10.getDisplayName())) {
                return c10.getDisplayName();
            }
        }
        return "";
    }

    private void appendCharacteristicsToFile(String filePath, String appendString  ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(appendString);
            System.out.println("Characteristics appended to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
