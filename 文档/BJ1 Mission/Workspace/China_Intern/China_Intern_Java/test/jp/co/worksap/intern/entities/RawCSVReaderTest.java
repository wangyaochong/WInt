package jp.co.worksap.intern.entities;

import java.io.FileReader;
import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

public class RawCSVReaderTest {
	private static final String ADDRESS_FILE = Constants.DEFAULT_CSV_FOLDER
			+ "JOB_TYPE_MST.csv";

	@Test
	public void readJobTypeMstTable() throws IOException {

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE),
				Constants.DEFAULT_CSV_SEPARATOR);
		String[] nextLine;
		boolean isTitleLine = true;
		int index = 1;
		while ((nextLine = reader.readNext()) != null) {
			if (isTitleLine) {
				System.out.println("JOB TYPE MASTER Table Columns : ["
						+ nextLine[0] + ", " + nextLine[1] + "," + nextLine[2]
						+ "," + nextLine[3] + ", " + nextLine[4] + "]");
				isTitleLine = false;
			} else {
				System.out.println("JOB TYPE Record: #" + (index++)
						+ "\nJOB TYPE ID: [" + nextLine[0] + "]\nJOB NAME: ["
						+ nextLine[1] + "]\nCan do Casher: [" + nextLine[2]
						+ "]\nCan do Delivery: [" + nextLine[3]
						+ "]\nCan do Cook: [" + nextLine[4] + "]\n");
			}
		}

	}
}
