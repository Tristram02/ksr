package org.example.project2.logic.linguistics;

public class DataEntry {

    private final Long id;
    private final String country;
    private final String continent;
    private final Integer year;
    private final double coalChangeProdTwh;
    private final double coalProdPerCapita;
    private final double coalProd;
    private final double gasChangeProdTwh;
    private final double gasProdPerCapita;
    private final double gasProd;
    private final double oilChangeProdTwh;
    private final double oilProdPerCapita;
    private final double oilProd;


    public DataEntry(Long id, String country, String continent, Integer year, double coalChangeProdTwh, double coalProdPerCapita, double coalProd, double gasChangeProdTwh, double gasProdPerCapita, double gasProd, double oilChangeProdTwh, double oilProdPerCapita, double oilProd) {
        this.id = id;
        this.continent = continent;
        this.country = country;
        this.year = year;
        this.coalChangeProdTwh = coalChangeProdTwh;
        this.coalProdPerCapita = coalProdPerCapita;
        this.coalProd = coalProd;
        this.gasChangeProdTwh = gasChangeProdTwh;
        this.gasProdPerCapita = gasProdPerCapita;
        this.gasProd = gasProd;
        this.oilChangeProdTwh = oilChangeProdTwh;
        this.oilProdPerCapita = oilProdPerCapita;
        this.oilProd = oilProd;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() { return continent; }

    public Integer getYear() {
        return year;
    }

    public double getCoalChangeProdTwh() {
        return coalChangeProdTwh;
    }

    public double getCoalProdPerCapita() {
        return coalProdPerCapita;
    }

    public double getCoalProd() {
        return coalProd;
    }

    public double getGasChangeProdTwh() {
        return gasChangeProdTwh;
    }

    public double getGasProdPerCapita() {
        return gasProdPerCapita;
    }

    public double getGasProd() {
        return gasProd;
    }

    public double getOilChangeProdTwh() {
        return oilChangeProdTwh;
    }

    public double getOilProdPerCapita() {
        return oilProdPerCapita;
    }

    public double getOilProd() {
        return oilProd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataEntry{");
        sb.append("id=").append(id);
        sb.append(", country='").append(country).append('\'');
        sb.append(", continent='").append(continent).append('\'');
        sb.append(", year=").append(year);
        sb.append(", coalChangeProdTwh=").append(coalChangeProdTwh);
        sb.append(", coalProdPerCapita=").append(coalProdPerCapita);
        sb.append(", coalProd=").append(coalProd);
        sb.append(", gasChangeProdTwh=").append(gasChangeProdTwh);
        sb.append(", gasProdPerCapita=").append(gasProdPerCapita);
        sb.append(", gasProd=").append(gasProd);
        sb.append(", oilChangeProdTwh=").append(oilChangeProdTwh);
        sb.append(", oilProdPerCapita=").append(oilProdPerCapita);
        sb.append(", oilProd=").append(oilProd);
        sb.append('}');
        return sb.toString();
    }

    public double getValueByName(String variableName) {
        switch (variableName) {
            case "Annual change in coal production":
                return coalChangeProdTwh;
            case "Coal production per capita":
                return coalProdPerCapita;
            case "Coal production":
                return coalProd;
            case "Annual change in gas production":
                return gasChangeProdTwh;
            case "Gas production per capita":
                return gasProdPerCapita;
            case "Gas production":
                return gasProd;
            case "Annual change in oil production":
                return oilChangeProdTwh;
            case "Oil production per capita":
                return oilProdPerCapita;
            case "Oil production":
                return oilProd;
            default: {
                System.out.println(variableName);
                throw new IllegalArgumentException("There is no variable with this name!");
            }

        }
    }
}
