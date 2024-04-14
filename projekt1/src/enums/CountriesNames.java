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

    public static CountriesNames fromDisplayName(String displayName) {
        for (CountriesNames country : CountriesNames.values()) {
            if (country.getDisplayName().equals(displayName)) {
                return country;
            }
        }
        throw new IllegalArgumentException("No enum constant " + CountriesNames.class.getCanonicalName() + "." + displayName);
    }
}
