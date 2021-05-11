package no.ntnu.idatg2001.miniprosjekt.postalcode.persistence;

import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for handling TSV (tab-separated-values) files.
 * Assuming that the TSV file will always have the data
 * on the same indexes.
 *
 * @author 10042
 * @version 11.05.2021
 */
public class TSVFileHandler implements Storage {
    private static final String DELIMITER = "\t";
    private String fileName;

    /**
     * Instantiates a new Tsv file handler.
     *
     * @param fileName the file name
     */
    public TSVFileHandler(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method for reading from the TSV-file.
     *
     * @return A list of postal codes read from the file.
     * @throws FileNotFoundException if file with given filename is inaccessible.
     */
    @Override
    public List<PostalCode> readFromStorage() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        List<PostalCode> postalCodes = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(DELIMITER);
            try {
                // index 0, index 1 and index 3 of the TSV file are zip code, city and municipality.
                PostalCode newPostalCode = new PostalCode(data[0], data[1], data[3]);
                postalCodes.add(newPostalCode);
            } catch (IndexOutOfBoundsException ioube) {
                System.err.println("Had problems parsing parts of the file, ignoring that line");
            } catch (IllegalArgumentException iae) {
                System.err.println("Postal code was invalid, ignoring that postal code");
            }
        }
        return postalCodes;
    }
}
