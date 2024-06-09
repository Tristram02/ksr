package org.example.project2.database;

import org.example.project2.logic.linguistics.DataEntry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    final String path;

    public CsvReader(String path) {
        this.path = path;
    }

    public List<DataEntry> readData() {
        List<DataEntry> dataEntries = new ArrayList<>();
        try (Reader reader = new FileReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())) {
            Long id = 1L;
            for (CSVRecord csvRecord : csvParser) {
                String country = csvRecord.get("COUNTRY");
                String continent = csvRecord.get("CONTINENT");
                int year = Integer.parseInt(csvRecord.get("YEAR"));
                double coalProductionChangeInTwh = Double.parseDouble(csvRecord.get("COAL_PRODUCTION_CHANGE_IN_TWH"));
                double coalProductionPerCapita = Double.parseDouble(csvRecord.get("COAL_PRODUCTION_PER_CAPITA"));
                double coalProduction = Double.parseDouble(csvRecord.get("COAL_PRODUCTION"));
                double gasProductionChangeInTwh = Double.parseDouble(csvRecord.get("GAS_PRODUCTION_CHANGE_IN_TWH"));
                double gasProductionPerCapita = Double.parseDouble(csvRecord.get("GAS_PRODUCTION_PER_CAPITA"));
                double gasProduction = Double.parseDouble(csvRecord.get("GAS_PRODUCTION"));
                double oilProductionChangeInTwh = Double.parseDouble(csvRecord.get("OIL_PRODUCTION_CHANGE_IN_TWH"));
                double oilProductionPerCapita = Double.parseDouble(csvRecord.get("OIL_PRODUCTION_PER_CAPITA"));
                double oilProduction = Double.parseDouble(csvRecord.get("OIL_PRODUCTION"));

                DataEntry dataEntry = new DataEntry(id, country, continent, year, coalProductionChangeInTwh, coalProductionPerCapita,
                        coalProduction, gasProductionChangeInTwh, gasProductionPerCapita, gasProduction, oilProductionChangeInTwh,
                        oilProductionPerCapita, oilProduction);
                id++;
                dataEntries.add(dataEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataEntries;
    }
}
