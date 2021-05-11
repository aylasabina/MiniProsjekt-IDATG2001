package no.ntnu.idatg2001.miniprosjekt.postalcode.persistence;

import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCode;

import java.io.IOException;
import java.util.List;

/**
 * An interface for handling storage of postal codes.
 *
 * @author 10042
 * @version 11.05.2021
 */
public interface Storage {

    /**
     * Method for reading postal codes from a storage.
     *
     * @return the list of postal codes that are read from the storage.
     * @throws IOException if unable to read from the storage.
     */
    List<PostalCode> readFromStorage() throws IOException;
}
