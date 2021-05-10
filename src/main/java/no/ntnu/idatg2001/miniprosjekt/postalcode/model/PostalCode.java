package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import java.util.regex.Pattern;

/**
 * Class for handling a postal code. A postal code has a
 * zip code (postnummer), city (poststed) and a municipality (kommune).
 *
 * @author 10042
 * @version 10.05.2021
 */
public class PostalCode {
    private String zipCode; // norsk: postkode
    private String city; // norsk: poststed
    private String municipality; // norsk: kommune

    /**
     * Instantiates a new Postal code.
     *
     * @param zipCode      the zip code
     * @param city         the city
     * @param municipality the municipality
     */
    public PostalCode(String zipCode, String city, String municipality) {
        setZipCode(zipCode);
        setCity(city);
        setMunicipality(municipality);
    }

    /**
     * Sets the zip code. If the given zip code does not match
     * the correct pattern, an IllegalArgumentException is thrown.
     * The given zip code matches when it only contains numbers,
     * and is exactly 4 digits long.
     *
     * @param zipCode the zip code to be set
     * @throws IllegalArgumentException if given zip code does not match
     *                                  the correct pattern.
     */
    private void setZipCode(String zipCode) {
        if(Pattern.compile("[0-9]{4}").matcher(zipCode).matches()) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("Postal code is not in the correct format.");
        }
    }

    /**
     * Sets the city. If the given city is blank, an
     * IllegalArgumentException is thrown.
     *
     * @param city the city to be set
     * @throws IllegalArgumentException if the given city is blank.
     */
    private void setCity(String city) {
        if(city.isBlank()) {
            throw new IllegalArgumentException("City cannot be blank.");
        } else {
            this.city = city;
        }
    }

    /**
     * Sets the municipality. If the given municipality is blank, an
     * IllegalArgumentException is thrown.
     *
     * @param municipality the municipality to be set
     * @throws IllegalArgumentException if the given municipality is blank.
     */
    private void setMunicipality(String municipality) {
        if(municipality.isBlank()) {
            throw new IllegalArgumentException("Municipality cannot be blank.");
        }
        this.municipality = municipality;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets municipality.
     *
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }
}
