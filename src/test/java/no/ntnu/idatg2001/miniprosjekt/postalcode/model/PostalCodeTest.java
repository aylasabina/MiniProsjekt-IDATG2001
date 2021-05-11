package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing PostalCode-class.
 */
class PostalCodeTest {

    /**
     * Nested class for positive testing of the constructor.
     */
    @Nested
    class PositiveConstructorTest {
        /**
         * Test the constructor with valid parameters.
         * This implicitly tests the private setters because they
         * are used in the constructor.
         * This should not throw an Exception.
         */
        @Test
        @DisplayName("Test the constructor with valid parameters.")
        void constructWithValidParametersTest() {
            // should not throw an exception.
            assertDoesNotThrow(() -> new PostalCode("4321", "Roseby", "Blomsterland"));
        }

        /**
         * Nested class for testing all getters.
         */
        @Nested
        class PostalCodeGettersTest {

            private PostalCode postalCode;

            /**
             * Setup before each test in this nested class that
             * creates the object to test on.
             */
            @BeforeEach
            public void setup() {
                postalCode = new PostalCode("4321", "Roseby", "Blomsterland");
            }

            /**
             * Test getting the zip code
             */
            @Test
            @DisplayName("Test get zip code")
            void getZipCodeTest() {
                assertSame("4321", postalCode.getZipCode());
            }

            /**
             * Test getting the city
             */
            @Test
            @DisplayName("Test get city")
            void getCityTest() {
                assertSame("Roseby", postalCode.getCity());
            }

            /**
             * Test getting the municipality.
             */
            @Test
            @DisplayName("Test get municipality")
            void getMunicipalityTest() {
                assertSame("Blomsterland", postalCode.getMunicipality());
            }
        }
    }

    /**
     * Nested class for negative testing of the constructor. Will
     * implicitly test all the private setters, because they are
     * used in the constructor.
     */
    @Nested
    class NegativeConstructorTest {

        /**
         * Test the constructor with invalid (blank) city,
         * this should throw IllegalArgumentException.
         */
        @Test
        void constructWithBlankCityTest() {
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("4321", "", "Blomsterland"));

            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("4321", "    ", "Blomsterland"));
        }

        /**
         * Test the constructor with invalid (blank) municipality,
         * this should throw IllegalArgumentException.
         */
        @Test
        void constructWithBlankMunicipalityTest() {
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("4321", "Roseby", ""));

            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("4321", "Roseby", "    "));
        }

        /**
         * Test the constructor with invalid zip code,
         * this should throw IllegalArgumentException.
         */
        @Test
        void constructWithBlankZipCodeTest() {
            // Test with empty string
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("", "Roseby", "Blomsterland"));

            // Test with only whitespaces
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("    ", "Roseby", "Blomsterland"));

            // Test with too few numbers
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("123", "Roseby", "Blomsterland"));

            // Test with too many numbers
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("12345", "Roseby", "Blomsterland"));

            // Test with letters instead of numbers
            assertThrows(IllegalArgumentException.class,
                    () -> new PostalCode("abcd", "Roseby", "Blomsterland"));
        }
    }
}