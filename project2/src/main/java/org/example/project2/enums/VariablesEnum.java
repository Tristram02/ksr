package org.example.project2.enums;

public enum VariablesEnum {

    ANN_COAL_CHANGE_PROD_TWH("annual change in coal production"),
    COAL_PROD_PER_CAPITA("coal production per capita"),
    COAL_PROD("coal production"),
    ANN_GAS_CHANGE_PROD_TWH("annual change in gas production"),
    GAS_PROD_PER_CAPITA("gas production per capita"),
    GAS_PROD("gas production"),
    ANN_OIL_CHANGE_PROD_TWH("annual change in oil production"),
    OIL_PROD_PER_CAPITA("oil production per capita"),
    OIL_PROD("oil production");

    private final String name;

    VariablesEnum(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
