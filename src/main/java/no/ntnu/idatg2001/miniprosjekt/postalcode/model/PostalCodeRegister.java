package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import no.ntnu.idatg2001.miniprosjekt.postalcode.persistence.Storage;
import no.ntnu.idatg2001.miniprosjekt.postalcode.persistence.TSVFileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for handling a register of postal codes.
 *
 * @author 10042
 * @version 11.05.2021
 */
public class PostalCodeRegister {
    // Instance used for Singleton
    private static PostalCodeRegister postalCodeRegisterInstance;
    private final Storage storage = new TSVFileHandler("postalcodes.txt");
    private ArrayList<PostalCode> postalCodes;


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
        if (postalCode != null) {
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

    /**
     * Gets the postal codes.
     * @return the list of postal codes.
     */
    public List<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    /**
     * Method for searching for matching postal codes.
     * Different search modes have different ways of
     * finding matches.
     *
     * @param searchEnum the search mode
     * @param searchString the search string
     * @return a list of postal codes that match
     */
    public List<PostalCode> search(SearchEnum searchEnum, String searchString) {
        // find postal codes that start with the given search string, this is default
        FindingFunction findingFunction = String::startsWith;

        switch (searchEnum) {
            case END:
                // find postal codes that end with given search string
                findingFunction = String::endsWith;
                break;
            case CONTAIN:
                // find postal codes that contain the given search string
                findingFunction = String::contains;
                break;
            case EXACT:
                // find postal codes that match (ignoring the case) exactly the given search string
                findingFunction = String::equals;
                break;
        }
        return findPostalCodes(searchString, findingFunction);
    }

    /**
     * Find the postal codes that match a given string by a given
     * function.
     *
     * @param searchString the search string
     * @param findingFunction the function for finding matches
     * @return a list of postal codes matching by the given function.
     */
    private List<PostalCode> findPostalCodes(String searchString, FindingFunction findingFunction) {
        return postalCodes.stream()
                .filter(postalCode ->
                        findingFunction.find(postalCode.getZipCode(), searchString) ||
                                findingFunction.find(postalCode.getCity().toUpperCase(), searchString.toUpperCase())
                ).collect(Collectors.toList());
    }

    //Functional interface used for storing lambda function in search-method.
    private interface FindingFunction {
        boolean find(String string, String searchString);
    }
}
