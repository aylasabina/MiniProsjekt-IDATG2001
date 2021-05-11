package no.ntnu.idatg2001.miniprosjekt.postalcode.model;


/**
 * The Search enum, defines different search modes.
 *
 * @author 10042
 * @version 11.05.2021
 */
public enum SearchEnum {
    /**
     * The start search enum.
     */
    START("Starts with"),
    /**
     * The end search enum.
     */
    END("Ends with"),
    /**
     * Contain search enum.
     */
    CONTAIN("Contains"),
    /**
     * Exact search enum.
     */
    EXACT("Exact");

    private String mode;

    /**
     * Constructor for search enum.
     *
     * @param mode A string describing the search mode.
     */
    private SearchEnum(String mode) {
        this.mode = mode;
    }

    /**
     * Gets the search mode.
     *
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return this.getMode();
    }
}
