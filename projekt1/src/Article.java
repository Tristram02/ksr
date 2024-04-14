public class Article {

    String country;
    FeaturesVector features;
    String countryPrediction;
    Integer numberOfWords;

    public Article() {
        this.country = null;
        this.features = null;
        this.countryPrediction = null;
        this.numberOfWords = null;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryPrediction() {
        return countryPrediction;
    }

    public FeaturesVector getFeatures() {
        return features;
    }

    public Integer getNumberOfWords() {
        return numberOfWords;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryPrediction(String countryPrediction) {
        this.countryPrediction = countryPrediction;
    }

    public void setFeatures(FeaturesVector features) {
        this.features = features;
    }

    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
}
