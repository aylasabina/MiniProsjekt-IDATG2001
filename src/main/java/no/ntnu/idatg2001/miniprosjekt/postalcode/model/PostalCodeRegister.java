package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import no.ntnu.idatg2001.miniprosjekt.postalcode.persistence.Storage;
import no.ntnu.idatg2001.miniprosjekt.postalcode.persistence.TSVFileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A class for handling a register of postal codes.
 *
 * @author 10042
 * @version 11.05.2021
 */
public class PostalCodeRegister {
    // Instance used for Singleton
    private static PostalCodeRegister postalCodeRegisterInstance;
    private ArrayList<PostalCode> postalCodes;
    private final Storage storage = new TSVFileHandler("postalcodes.txt");


    private PostalCodeRegister() {
        this.postalCodes = new ArrayList<>();
    }

    /**
     * Gets postal code register instance.
     *
     * @return the postal code register instance
     */
    // Using Singleton pattern with lazy instantiation (thread safe)
    public static synchronized PostalCodeRegister getPostalCodeRegisterInstance() {
        if (postalCodeRegisterInstance == null) {
            postalCodeRegisterInstance = new PostalCodeRegister();
        }
        return postalCodeRegisterInstance;
    }

    /**
     * Add a postal code to the hashmap, if the postal
     * code is not equal to <code>null</code>.
     *
     * @param postalCode the postal code to be added.
     */
    private void addPostalCode(PostalCode postalCode) {
        if(postalCode != null) {
            postalCodes.add(postalCode);
        }
    }

    /**
     * Gets postal codes from file.
     *
     * @return the postal codes from file
     */
    public List<PostalCode> getPostalCodesFromFile() throws IOException {
        this.postalCodes = (ArrayList<PostalCode>) storage.readFromStorage();
        return this.postalCodes;
    }

    public List<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    /**
     * Searches the postal code list with a given search string.
     * Returns a hashset of the postal codes with a zip code or
     * city name that starts with the given search string.
     *
     * @param searchString the string to search with.
     * @return a hashset of matches.
     */
    public HashSet<PostalCode> searchPostalCodes(String searchString) {
        HashSet<PostalCode> foundPostalCodesSet = new HashSet<>();
        for(PostalCode postalCode : postalCodes) {
            if(postalCode.getZipCode().startsWith(searchString) ||
            postalCode.getCity().toLowerCase().startsWith(searchString)) {
                foundPostalCodesSet.add(postalCode);
            }
        }
        return foundPostalCodesSet;
    }
}
