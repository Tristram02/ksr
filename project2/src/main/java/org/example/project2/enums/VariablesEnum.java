package org.example.project2.enums;

public enum VariablesEnum {

    ANN_COAL_CHANGE_PROD_TWH("Annual change in coal production"),
    COAL_PROD_PER_CAPITA("Coal production per capita"),
    COAL_PROD("Coal production"),
    ANN_GAS_CHANGE_PROD_TWH("Annual change in gas production"),
    GAS_PROD_PER_CAPITA("Gas production per capita"),
    GAS_PROD("Gas production"),
    ANN_OIL_CHANGE_PROD_TWH("Annual change in oil production"),
    OIL_PROD_PER_CAPITA("Oil production per capita"),
    OIL_PROD("Oil production");

    private final String name;

    VariablesEnum(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
