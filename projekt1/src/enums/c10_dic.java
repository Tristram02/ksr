package enums;

public enum c10_dic {

    LONDON("London"),
    PARIS("Paris"),
    WASHINGTON("Washington"),
    OTTAWA("Ottawa"),
    TOKYO("Tokyo"),
    BONN("Bonn");

    private final String displayName;

    c10_dic(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
