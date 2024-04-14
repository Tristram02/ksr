import enums.C1_9_dic;
import enums.c10_dic;

import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

    public List<Article> extractCharacteristicsForAllArticles() {
        List<Article> articles = new ArrayList<>();
        System.out.println(getArticlesDirectoryPath());
        File directory = new File(getArticlesDirectoryPath());
        if (!directory.isDirectory()) {
            System.out.println("Specified path is not a directory.");
            return null;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return null;
        }

        for (File file : files) {
            if (file.isFile()) {
                articles.add(extractCharacteristicsFromArticle(file.getAbsolutePath(), file.getName()));
            }
        }
        System.out.println("Characteristics extracted and saved successfully.");
        return articles;
    }

    private Article extractCharacteristicsFromArticle(String absolutePath, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            Article article = new Article();
            FeaturesVector features = new FeaturesVector();
            String line;
            String articleDateline = "";
            StringBuilder articleBody = new StringBuilder();
            int lineCounter = 0;

            while ((line = reader.readLine()) != null) {
                if (lineCounter == 0) {
                    article.setCountry(line);
                    articleBody.append(line).append("\n");
                } else if (lineCounter == 1) {
                    articleDateline = line;
                } else if (lineCounter > 1) {
                    articleBody.append(line).append("\n");
                }
                lineCounter++;
            }

            StringBuilder filteredArticleBody = new StringBuilder(filterStopList(articleBody));
            long numberOfWordsInArticle = filteredArticleBody.toString().trim().split("\\s+").length;
            article.setNumberOfWords((int) numberOfWordsInArticle);
            System.out.println("filename: " + filename);

            Feature c1 = new Feature(extractC1(filteredArticleBody.toString(), C1_9_dic.values()));

            Feature c2 = new Feature(extractLicznosc(filteredArticleBody.toString(), "c2_dic.json"));

            Feature c3 = new Feature(extractLicznosc(filteredArticleBody.toString(), "c3_dic.json"));

            Feature c4 = new Feature(extractCzestosc(filteredArticleBody.toString(), "c4_dic.json", (int) numberOfWordsInArticle));

            Feature c5 = new Feature(extractLicznosc(filteredArticleBody.toString(), "c5_dic.json"));

            Feature c6 = new Feature(extractCzestosc(filteredArticleBody.toString(), "c6_dic.json", (int) numberOfWordsInArticle));

            Feature c7 = new Feature(extractC7(articleDateline));

            Feature c8 = new Feature(extractC8(articleDateline));

            Feature c9 = new Feature(extractC9(filteredArticleBody.toString(), C1_9_dic.values()));

            Feature c10 = new Feature(extractC10(filteredArticleBody.toString(), c10_dic.values()));

            StringBuilder characteristics = new StringBuilder();
            characteristics.append("\nc1: ").append(c1.getNumber())
                    .append("\nc2: ").append(c2.getNumber())
                    .append("\nc3: ").append(c3.getNumber())
                    .append("\nc4: ").append(c4.getNumber())
                    .append("\nc5: ").append(c5.getNumber())
                    .append("\nc6: ").append(c6.getNumber())
                    .append("\nc7: ").append(c7.getText())
                    .append("\nc8: ").append(c8.getText())
                    .append("\nc9: ").append(c9.getValue())
                    .append("\nc10: ").append(c10.getText());

            appendCharacteristicsToFile(absolutePath, characteristics.toString());

            features.add(c1);
            features.add(c2);
            features.add(c3);
            features.add(c4);
            features.add(c5);
            features.add(c6);
            features.add(c7);
            features.add(c8);
            features.add(c9);
            features.add(c10);

            article.setFeatures(features);

            return article;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer extractLicznosc(String articleBody, String dictonaryFileName) {
        Map<String, Integer> counts = new HashMap<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get("src/dictonaries/" + dictonaryFileName)));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleBody.toLowerCase().contains(value.toLowerCase())) {
                        String regex = "\\b" + Pattern.quote(value.toLowerCase()) + "\\b";
                        int count = (articleBody.toLowerCase().split(regex, -1).length) - 1;
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
            String content = new String(Files.readAllBytes(Paths.get("src/dictonaries/" + dictonaryFileName)));
            JSONObject jsonObject = new JSONObject(content);

            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONArray array = jsonObject.getJSONArray(key);

                for(int i = 0; i < array.length(); i++){
                    String value = array.getString(i);
                    if (articleBody.toLowerCase().contains(value.toLowerCase())) {
                        String regex = "\\b" + Pattern.quote(value.toLowerCase()) + "\\b";
                        int count = (articleBody.toLowerCase().split(regex, -1).length) - 1;
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
        try (BufferedReader reader = new BufferedReader(new FileReader("src/dictonaries/stop_words_english.txt"))) {
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
            if (articleBody.toLowerCase().contains(c1.getDisplayName().toLowerCase())) {
                String regex = "\\b" + Pattern.quote(c1.getDisplayName().toLowerCase()) + "\\b";
                int count = (articleBody.toLowerCase().split(regex, -1).length) - 1;
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
            String content = new String(Files.readAllBytes(Paths.get("src/dictonaries/c7_dic.json")));
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
            String content = new String(Files.readAllBytes(Paths.get("src/dictonaries/c8_dic.json")));
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
            if (articleBody.toLowerCase().contains(c9.getDisplayName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String extractC10(String articleBody, c10_dic[] c10_dictonary) {
        for (c10_dic c10 : c10_dictonary) {
            if (articleBody.toLowerCase().contains(c10.getDisplayName().toLowerCase())) {
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
