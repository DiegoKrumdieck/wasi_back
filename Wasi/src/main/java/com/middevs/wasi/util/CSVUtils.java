package com.middevs.wasi.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.middevs.wasi.table.RealEstate;
import com.opencsv.CSVWriter;

public class CSVUtils {
	public static void writeRealEstateDataToCSV(List<RealEstate> realEstates, String pathToExport) throws IOException {
		File file = new File(pathToExport);
        // create FileWriter object with file as parameter
        FileWriter outputfile = new FileWriter(file);
  
        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outputfile, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
  
        // adding header to csv
        String[] header = { "id", "lat", "lng" };
        writer.writeNext(header);
  
        for(RealEstate re : realEstates) {
        	// add data to csv
            String[] data = { re.getIdRealEstate().toString(), re.getLatitudeRealEstate(),
            		re.getLongitudeRealEstate() };
            writer.writeNext(data);
        }
        
  
        // closing writer connection
        writer.close();

	}
}
