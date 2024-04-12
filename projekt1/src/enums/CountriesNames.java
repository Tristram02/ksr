package enums;

public enum CountriesNames {
    USA("usa"),
    JAPAN("japan"),
    CANADA("canada"),
    FRANCE("france"),
    WEST_GERMANY("west-germany"),
    UK("uk");


    private final String displayName;

    CountriesNames(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
