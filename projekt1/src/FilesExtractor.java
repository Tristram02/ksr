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
    private boolean inOnlyTextTag = false;

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
        System.out.println("Starting articles extraction...");
        for (File file : files) {
            if (file.isFile()) {
                extractFromFile(file.getAbsolutePath(), outputDirPath, filterCountries, file.getName());
            }
        }
        System.out.println("Articles extracted and saved successfully.");
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
                    }
                    article.setLength(0);
                    isAllowedCountry = false;
                } else if (line.contains("</BODY>" )|| line.contains("</TEXT>")){
                    inBodyTag = false;
                    inOnlyTextTag = false;
                } else if (line.contains("<TEXT ")){
                    inOnlyTextTag = true;
                    article.append("\n");
                } else if (isAllowedCountry && containsTags(line)) {
                    appendArticleContent(article, line);
                } else if (inBodyTag && isAllowedCountry) {
                    article.append(line).append("\n");
                } else if (inOnlyTextTag && isAllowedCountry && !line.contains("<TITLE>")){
                    if(line.contains("/TITLE>") ){
                        article.append(line.substring(line.indexOf("/TITLE>") + 7)).append("\n");
                    } else {
                        article.append(line).append("\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveArticleToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendArticleContent(StringBuilder article, String line) {
        if (line.contains("DATELINE>")) {
            if(line.contains("</DATELINE>") && line.contains("<DATELINE>")){
                article.append(line, line.indexOf("<DATELINE>") + 10, line.lastIndexOf("</DATELINE>")).append("\n");
                article.append(line.substring(line.indexOf("<BODY>") + 6)).append("\n");
                inBodyTag = true;
            } else if(line.contains("<DATELINE>")){
                article.append(line.substring(line.indexOf("<DATELINE>") + 10)).append("\n");

            } else if (line.contains("</DATELINE>")) {
                article.append(line.substring(line.lastIndexOf("</DATELINE><BODY>") + 17)).append("\n");
                inBodyTag = true;
            }

        }
    }

    private boolean containsTags(String line) {
        return line.contains("DATELINE>") || line.contains("<BODY>") || line.contains("<TEXT ");
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
