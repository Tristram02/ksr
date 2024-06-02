package org.example.project2.enums;

public enum ContinentsEnum {

    AFRICA("Africa"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    OCEANIA("Oceania"),
    SOUTH_AMERICA("South America");



    private final String name;

    ContinentsEnum(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}

