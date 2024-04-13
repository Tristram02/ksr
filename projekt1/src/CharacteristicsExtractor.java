import enums.C1_9_dic;
import enums.c10_dic;

import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacteristicsExtractor {

    String articlesDirectoryPath;

    public CharacteristicsExtractor(String articlesDirectoryPath) {
        this.articlesDirectoryPath = articlesDirectoryPath;
    }


    public String getArticlesDirectoryPath() {
        return articlesDirectoryPath;
    }

    public void extractCharacteristicsForAllArticles() {
        File directory = new File(getArticlesDirectoryPath());
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
        System.out.println("Characteristics extracted and saved successfully.");
    }

    private void extractCharacteristicsFromArticle(String absolutePath, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            String articleDateline = "";
            StringBuilder articleBody = new StringBuilder();
            int lineCounter = 0;

            while ((line = reader.readLine()) != null) {
                if (lineCounter == 1) {
                    articleDateline = line;
                } else if (lineCounter > 1) {
                    articleBody.append(line).append("\n");
                }
                lineCounter++;
            }

            StringBuilder filteredArticleBody = new StringBuilder(filterStopList(articleBody));
            long numberOfWordsInArticle = filteredArticleBody.toString().trim().split("\\s+").length;

            System.out.println("filename: " + filename);

            Integer c1 = extractC1(filteredArticleBody.toString(), C1_9_dic.values());

            Integer c2 = extractLicznosc(filteredArticleBody.toString(), "c2_dic.json");

            Integer c3 = extractLicznosc(filteredArticleBody.toString(), "c3_dic.json");

            Double c4 = extractCzestosc(filteredArticleBody.toString(), "c4_dic.json", (int) numberOfWordsInArticle);

            Integer c5 = extractLicznosc(filteredArticleBody.toString(), "c5_dic.json");

            Double c6 = extractCzestosc(filteredArticleBody.toString(), "c6_dic.json", (int) numberOfWordsInArticle);

            String c7 = extractC7(articleDateline);

            String c8 = extractC8(articleDateline);

            Boolean c9 = extractC9(filteredArticleBody.toString(), C1_9_dic.values());

            String c10 = extractC10(filteredArticleBody.toString(), c10_dic.values());

            StringBuilder characteristics = new StringBuilder();
            characteristics.append("\n").append(c1).append("\n").append(c2).append("\n")
                    .append(c3).append("\n").append(c4).append("\n").append(c5).append("\n")
                    .append(c6).append("\n").append(c7).append("\n").append(c8).append("\n")
                    .append(c9).append("\n").append(c10);
            appendCharacteristicsToFile(absolutePath, characteristics.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer extractLicznosc(String articleBody, String dictonaryFileName) {
        Map<String, Integer> counts = new HashMap<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get("../dictonaries/" + dictonaryFileName)));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleBody.contains(value)) {
                        String regex = "\\b" + Pattern.quote(value) + "\\b";
                        int count = (articleBody.split(regex, -1).length) - 1;
                        counts.put(value, count);
                    } else {
                        counts.put(value, 0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return counts.values().stream()
                .max(Integer::compare)
                .orElse(0);
    }


    private Double extractCzestosc(String articleBody, String dictonaryFileName, Integer wordsInArticleCount) {
        Map<String, Double> counts = new HashMap<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get("../dictonaries/" + dictonaryFileName)));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleBody.contains(value)) {
                        String regex = "\\b" + Pattern.quote(value) + "\\b";
                        int count = (articleBody.split(regex, -1).length) - 1;
                        counts.put(value, count / (double) wordsInArticleCount);
                    } else {
                        counts.put(value, 0.0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return counts.values().stream()
                .max(Double::compare)
                .orElse(0.0);
    }


    private StringBuilder filterStopList(StringBuilder text) {
        StringBuilder filteredText = new StringBuilder(text.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\PLIKI\\semestr_6\\KSRy\\repo\\ksr\\projekt1\\dictonaries\\stop_words_english.txt"))) {
            String line;
            String lowerCaseText = filteredText.toString().toLowerCase();
            while ((line = reader.readLine()) != null) {
                String pattern = "\\b" + Pattern.quote(line.toLowerCase()) + "\\b";
                Pattern wordPattern = Pattern.compile(pattern);
                Matcher matcher = wordPattern.matcher(lowerCaseText);

                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    filteredText.replace(start, end, "");
                    lowerCaseText = filteredText.toString().toLowerCase();
                    matcher = wordPattern.matcher(lowerCaseText);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filteredText;
    }

    private Integer extractC1(String articleBody, C1_9_dic[] c1_dictonary) {
        Map<C1_9_dic, Integer> counts = new EnumMap<>(C1_9_dic.class);

        for (C1_9_dic c1 : c1_dictonary) {
            if (articleBody.contains(c1.getDisplayName())) {
                String regex = "\\b" + Pattern.quote(c1.getDisplayName()) + "\\b";
                int count = (articleBody.split(regex, -1).length) - 1;
                counts.put(c1, count);
            } else {
                counts.put(c1, 0);
            }
        }

        return counts.values().stream()
                .max(Integer::compare)
                .orElse(0);
    }

    private String extractC7(String articleDateline) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("../dictonaries/c7_dic.json")));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleDateline.toLowerCase().contains(value.toLowerCase())) {
                        return value;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String extractC8(String articleDateline) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("../dictonaries/c8_dic.json")));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleDateline.toLowerCase().contains(value.toLowerCase())) {
                        return value;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private Boolean extractC9(String articleBody, C1_9_dic[] c9_dictonary) {
        for (C1_9_dic c9 : c9_dictonary) {
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

    private void appendCharacteristicsToFile(String filePath, String appendString) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(appendString);
            System.out.println("Characteristics appended to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
