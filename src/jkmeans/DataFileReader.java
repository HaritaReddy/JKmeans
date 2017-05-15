/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jkmeans;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *  This class holds methods for returning an array of data values from CSV/TSV files using Apache Commons library.
 * @author Robert Streetman
 */
public class DataFileReader {
    
    /**
     * 
     * @param dataFile, CSV file containing only numeric data to be clustered.
     * @return Array of values expressed as double values.
     */
    public static double[][] ReadeCSVFile(File dataFile) {
        double[][] data = null;
        FileReader reader = null;
        CSVParser parser = null;
        CSVFormat format = CSVFormat.DEFAULT;
        
        try {
            reader = new FileReader(dataFile);
            parser = new CSVParser(reader, format);
            
            List records = parser.getRecords();
            CSVRecord firstRecord = (CSVRecord) records.get(0);
            int numRecords = records.size();
            int numDimen = firstRecord.size();
            
            data = new double[numRecords][numDimen];
            
            for (int i = 0; i < numRecords; i++) {
                CSVRecord thisRecord = (CSVRecord) records.get(i);
                
                for (int j = 0; j < numDimen; j++) {
                    data[i][j] = Double.parseDouble(thisRecord.get(j));
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading data file: " + ex.getMessage() + "...");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("Error closing file reader: " + ex.getMessage() + "...");
                }
            }
            
            if (parser != null) {
                try {
                    parser.close();
                } catch (IOException ex) {
                    System.out.println("Error closing CSV parser: " + ex.getMessage() + "...");
                }
            }
        }
        
        return data;
    }
    
    /**
     * 
     * @param dataFile, TSV file containing only numeric data to be clustered.
     * @return Array of values expressed as double values.
     */
    public static double[][] ReadTSVFile(File dataFile) {
        double[][] data = null;
        
        return data;
    }
}
