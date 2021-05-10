package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A class for handling a register of postal codes.
 *
 * @author 10042
 * @version 10.05.2021
 */
public class PostalCodeRegister {
    // Instance used for Singleton
    private static PostalCodeRegister postalCodeRegisterInstance;
    private final ArrayList<PostalCode> postalCodes;


    private PostalCodeRegister() {
        this.postalCodes = new ArrayList<>();
        fillRegisterDummies();
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
    public List<PostalCode> getPostalCodesFromFile() {
        // temporary
        return null;
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

    /**
     * Temporary method for for filling register with dummies for testing purposes.
     */
    private void fillRegisterDummies() {
        postalCodes.add(new PostalCode("1234", "city1", "municipality1"));
        postalCodes.add(new PostalCode("2345", "city2", "municipality1"));
        postalCodes.add(new PostalCode("3456", "city2", "municipality3"));
        postalCodes.add(new PostalCode("4567", "city4", "municipality4"));
        postalCodes.add(new PostalCode("4456", "city5", "municipality5"));
    }
}
