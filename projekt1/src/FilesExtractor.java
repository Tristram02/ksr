import enums.CountriesNames;

import java.io.*;
import java.util.*;

public class FilesExtractor {

    String sgmlFilesPath;
    String outputDirPath;

    EnumSet<CountriesNames> filterCountries = EnumSet.of(CountriesNames.USA, CountriesNames.JAPAN, CountriesNames.CANADA, CountriesNames.FRANCE, CountriesNames.WEST_GERMANY, CountriesNames.UK);

    public FilesExtractor(String sgmlFilesPath, String outputDirPath) {
        this.sgmlFilesPath = sgmlFilesPath;
        this.outputDirPath = outputDirPath;
    }

    private boolean inBodyTag = false;

    public void extractFiles() {
        File directory = new File(sgmlFilesPath);
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
                extractFromFile(file.getAbsolutePath(), outputDirPath, filterCountries, file.getName());
            }
        }
    }
    private void extractFromFile(String filePath, String outputDirPath, EnumSet<CountriesNames> filterCountries, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder article = new StringBuilder();
            boolean isAllowedCountry = false;
            int articleCount = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains("<PLACES><D>")) {
                    String placesTagContent = line.substring(line.indexOf("<D>") + 3, line.lastIndexOf("</D>"));
                    isAllowedCountry = isAllowedCountry(placesTagContent, filterCountries);
                    if (isAllowedCountry) {
                        article.append(placesTagContent).append("\n");
                    }
                } else if (line.contains("</REUTERS>")) {
                    if (isAllowedCountry) {
                        saveArticleToFile(article.toString(), outputDirPath + "\\" + fileName.substring(0, fileName.lastIndexOf(".")) + "_article_" + articleCount + ".txt");
                        articleCount++;
                        System.out.println(article);
                    }
                    article.setLength(0);
                    isAllowedCountry = false;
                }else if (line.contains("<TEXT ")) {
                    article.setLength(0);
                    isAllowedCountry = false;
                } else if (line.contains("</BODY>")) {
                    inBodyTag = false;
                } else if (isAllowedCountry && containsTags(line)) {
                    appendArticleContent(article, line);
                } else if (inBodyTag && isAllowedCountry) {
                    article.append(line).append("\n");
                }
            }

            System.out.println("Articles extracted and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveArticleToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Article saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendArticleContent(StringBuilder article, String line) {
        if (line.contains("TITLE>")) {
            if(line.contains("</TITLE>") && line.contains("<TITLE>")){
                article.append(line, line.indexOf("<TITLE>") + 7, line.lastIndexOf("</TITLE>")).append("\n");
            } else if(line.contains("<TITLE>")){
                article.append(line.substring(line.indexOf("<TITLE>") + 7)).append("\n");
            } else if (line.contains("</TITLE>")) {
                article.append(line.substring(line.lastIndexOf("</TITLE>") + 8)).append("\n");
            }
        } else if (line.contains("DATELINE>")) {
            if(line.contains("</DATELINE>") && line.contains("<DATELINE>")){
                article.append(line, line.indexOf("<DATELINE>") + 10, line.lastIndexOf("</DATELINE>")).append("\n");
                article.append(line.substring(line.indexOf("<BODY>") + 6)).append("\n");
                inBodyTag = true;
            } else if(line.contains("<DATELINE>")){
                article.append(line.substring(line.indexOf("<DATELINE>") + 10)).append("\n");

            } else if (line.contains("</DATELINE>")) {
                article.append(line.substring(line.lastIndexOf("</DATELINE><BODY>") + 17)).append("\n");
            }

        }
    }

    private boolean containsTags(String line) {
        return line.contains("TITLE>") || line.contains("DATELINE>") || line.contains("<BODY>");
    }

    private static boolean isAllowedCountry(String placesTagContent, EnumSet<CountriesNames> allowedCountries) {
        for (CountriesNames country : allowedCountries) {
            if (placesTagContent.equals(country.getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    public String getSgmlFilesPath() {
        return sgmlFilesPath;
    }

    public String getOutputDirPath() {
        return outputDirPath;
    }
}
