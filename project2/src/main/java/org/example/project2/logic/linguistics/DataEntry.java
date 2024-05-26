package org.example.project2.logic.linguistics;

public class DataEntry {

    private final Long id;
    private final String country;
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


    public DataEntry(Long id, String country, Integer year, double coalChangeProdTwh, double coalProdPerCapita, double coalProd, double gasChangeProdTwh, double gasProdPerCapita, double gasProd, double oilChangeProdTwh, double oilProdPerCapita, double oilProd) {
        this.id = id;
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
}
