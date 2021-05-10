package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import java.util.ArrayList;
import java.util.HashMap;
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
}
