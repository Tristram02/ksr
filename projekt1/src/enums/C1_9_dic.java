package enums;

public enum C1_9_dic {
//    UK, United Kingdom, France, Canada, Japan, USA, United States of
//    America, West Germany, FRG, Federal Republic of Germany

    UK("UK"),
    UNITED_KINGDOM("United Kingdom"),
    FRANCE("France"),
    CANADA("Canada"),
    JAPAN("Japan"),
    USA("USA"),
    UNITED_STATES_OF_AMERICA("United States of America"),
    WEST_GERMANY("West Germany"),
    FRG("FRG"),
    FEDERAL_REPUBLIC_OF_GERMANY("Federal Republic of Germany");

    private final String displayName;

    C1_9_dic(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

