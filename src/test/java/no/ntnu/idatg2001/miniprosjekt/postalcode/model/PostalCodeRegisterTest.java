package no.ntnu.idatg2001.miniprosjekt.postalcode.model;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing PostalCodeRegister.
 */
class PostalCodeRegisterTest {
    private static final PostalCodeRegister postalCodeRegister = PostalCodeRegister.getPostalCodeRegisterInstance();
    private static PostalCode postalCode1;
    private static PostalCode postalCode2;
    private static PostalCode postalCode3;

    /**
     * Setting up some postal code objects in the postal code register.
     */
    @BeforeAll
    public static void setup() {
        postalCode1 = new PostalCode("1234", "Roseby", "Blomsterland");
        postalCode2 = new PostalCode("1223", "Liljeby", "Blomsterland");
        postalCode3 = new PostalCode("9876", "Løvetannby", "Blomsterland");

        postalCodeRegister.getPostalCodes().addAll(Arrays.asList(postalCode1, postalCode2, postalCode3));
    }

    /**
     * Positive test for getting an instance of a postal code register.
     */
    @Test
    @DisplayName("Test get a postal code register instance")
    void getPostalCodeRegisterInstanceTest() {
        // Check that the postal code register instance is not null.
        assertNotEquals(null, postalCodeRegister);
    }

    /**
     * Negative test for getting an instance of postal code register.
     * The new postal code register should not be created, and
     * only be the same instance as the original postal code register.
     */
    @Test
    @DisplayName("Test get a second postal code register instance")
    void getSecondPostalCodeRegisterInstanceTest() {
        PostalCodeRegister secondPostalCodeRegister = PostalCodeRegister.getPostalCodeRegisterInstance();
        // The postal code registers should be the same instance because of Singleton
        assertSame(postalCodeRegister, secondPostalCodeRegister);
    }

    /**
     * Nested class for positive testing of the search method.
     */
    @Nested
    class SearchPostalCodesPositiveTest {

        @Test
        @DisplayName("Test searching 'Starts with'-mode with matches")
        void searchStartWithResultsTest() {
            // searching by zip codes
            List<PostalCode> postalCodesStartWith12 =
                    postalCodeRegister.search(SearchEnum.START, "12");

            assertTrue(postalCodesStartWith12.containsAll(Arrays.asList(postalCode1, postalCode2)));
            assertEquals(2, postalCodesStartWith12.size());

            // searching by city
            List<PostalCode> postalCodesStartWithL =
                    postalCodeRegister.search(SearchEnum.START, "l");

            assertTrue(postalCodesStartWithL.containsAll(Arrays.asList(postalCode2, postalCode3)));
            assertEquals(2, postalCodesStartWithL.size());

            // Should contain all postal codes
            List<PostalCode> postalCodesStartWithBlank =
                    postalCodeRegister.search(SearchEnum.START, "");

            assertTrue(postalCodesStartWithBlank.containsAll(postalCodeRegister.getPostalCodes()));
        }

        @Test
        @DisplayName("Test searching 'Starts with'-mode without matches")
        void searchStartWithoutResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesStartWith2 =
                    postalCodeRegister.search(SearchEnum.START, "2");

            assertTrue(postalCodesStartWith2.isEmpty());

            // searching by city
            List<PostalCode> postalCodesStartWithBy =
                    postalCodeRegister.search(SearchEnum.START, "by");

            assertTrue(postalCodesStartWithBy.isEmpty());
        }

        @Test
        @DisplayName("Test searching 'Ends with'-mode with matches")
        void searchEndWithResultsTest() {
            // searching by city
            List<PostalCode> postalCodesEndWithEby =
                    postalCodeRegister.search(SearchEnum.END, "eby");

            assertTrue(postalCodesEndWithEby.containsAll(Arrays.asList(postalCode1, postalCode2)));
            assertEquals(2, postalCodesEndWithEby.size());

            // searching by zip code
            List<PostalCode> postalCodesEndWith6 =
                    postalCodeRegister.search(SearchEnum.END, "6");

            assertTrue(postalCodesEndWith6.contains(postalCode3));
            assertEquals(1, postalCodesEndWith6.size());

            // Should contain all postal codes
            List<PostalCode> postalCodesEndWithBlank =
                    postalCodeRegister.search(SearchEnum.END, "");

            assertTrue(postalCodesEndWithBlank.containsAll(postalCodeRegister.getPostalCodes()));
        }

        @Test
        @DisplayName("Test searching 'Ends with'-mode without matches")
        void searchEndWithoutResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesEndWith33 =
                    postalCodeRegister.search(SearchEnum.END, "33");

            assertTrue(postalCodesEndWith33.isEmpty());

            // searching by city
            List<PostalCode> postalCodesEndWithSby =
                    postalCodeRegister.search(SearchEnum.END, "sby");

            assertTrue(postalCodesEndWithSby.isEmpty());
        }

        @Test
        @DisplayName("Test searching 'Contains'-mode with matches")
        void searchContainsWithResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesContain23 =
                    postalCodeRegister.search(SearchEnum.CONTAIN, "23");

            assertTrue(postalCodesContain23.containsAll(Arrays.asList(postalCode1, postalCode2)));
            assertEquals(2, postalCodesContain23.size());

            // searching by city
            List<PostalCode> postalCodesContainE =
                    postalCodeRegister.search(SearchEnum.CONTAIN, "E");

            assertTrue(postalCodesContainE.containsAll(Arrays.asList(postalCode1, postalCode2, postalCode3)));
            assertEquals(3, postalCodesContainE.size());

            // Should contain all postal codes
            List<PostalCode> postalCodesContaimBlank =
                    postalCodeRegister.search(SearchEnum.CONTAIN, "");

            assertTrue(postalCodesContaimBlank.containsAll(postalCodeRegister.getPostalCodes()));
        }

        @Test
        @DisplayName("Test searching 'Contains'-mode without matches")
        void searchContainsWithoutResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesContain5 =
                    postalCodeRegister.search(SearchEnum.CONTAIN, "5");

            assertTrue(postalCodesContain5.isEmpty());

            // searching by city
            List<PostalCode> postalCodesContainEk =
                    postalCodeRegister.search(SearchEnum.CONTAIN, "ek");

            assertTrue(postalCodesContainEk.isEmpty());
        }

        @Test
        @DisplayName("Test searching 'Exact'-mode with matches")
        void searchExactWithResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesExact1234 =
                    postalCodeRegister.search(SearchEnum.EXACT, "1234");

            assertTrue(postalCodesExact1234.contains(postalCode1));
            assertEquals(1, postalCodesExact1234.size());

            // searching by city
            List<PostalCode> postalCodesExactRoseby =
                    postalCodeRegister.search(SearchEnum.EXACT, "roseby");

            assertTrue(postalCodesExactRoseby.contains(postalCode1));
            assertEquals(1, postalCodesExactRoseby.size());
        }

        @Test
        @DisplayName("Test searching 'Exact'-mode without matches")
        void searchExactWithoutResultsTest() {
            // searching by zip code
            List<PostalCode> postalCodesExact123 =
                    postalCodeRegister.search(SearchEnum.EXACT, "123");

            assertTrue(postalCodesExact123.isEmpty());

            // searching by city
            List<PostalCode> postalCodesExactRose =
                    postalCodeRegister.search(SearchEnum.EXACT, "rose");

            assertTrue(postalCodesExactRose.isEmpty());

            // empty string should contain no postal codes
            List<PostalCode> postalCodesExactBlank =
                    postalCodeRegister.search(SearchEnum.EXACT, "");

            assertTrue(postalCodesExactBlank.isEmpty());
        }
    }

    /**
     * Nested class for negative testing of the search method.
     */
    @Nested
    class SearchPostalCodesNegativeTest {

        /**
         * Test search with invalid characters, i.e characters
         * that are not numbers nor unicode letters.
         * This should return null for all search enums.
         */
        @Test
        @DisplayName("Test search with invalid characters")
        void searchInvalidCharacters() {
            // Some of the most significant invalid characters.
            List<String> invalidCharacters = Arrays
                    .asList("!", "?", ".", ",", "-",
                            ":", ";", "+", "/", "|",
                            "@", "#", "%", "&", "=",
                            "*", "\"", "\\", "^", "$",
                            ">", "<", "_", "~", "¨");

            // searching with every search mode enum
            for (SearchEnum searchEnum : SearchEnum.values()) {
                // searching with every invalid character in the list
                for (String invalidCharacter : invalidCharacters) {
                    // Every search should return null
                    if(postalCodeRegister.search(searchEnum, invalidCharacter) == null) {
                        assert(true);
                    } else {
                        fail("Failed on search mode " +
                                searchEnum.getMode() +
                                ", and character "
                                + invalidCharacter);
                    }
                }
            }
        }

        /**
         * Test searching with invalid characters mixed with numbers.
         * This should still return null for all search enums.
         */
        @Test
        @DisplayName("Test search with invalid characters mixed with numbers")
        void searchNumbersAndInvalidCharacters() {
            // testing with invalid character both on beginning, in middle and end.
            List<String> searchStrings = Arrays.asList("12!", "!12", "1!2");

            for(String searchString : searchStrings) {
                for(SearchEnum searchEnum : SearchEnum.values()) {
                    assertNull(postalCodeRegister.search(searchEnum, searchString));
                }
            }
        }

        /**
         * Test searching with invalid characters mixed with unicode letters.
         * This should still return null for all search enums.
         */
        @Test
        @DisplayName("Test search with invalid characters mixed with unicode letters")
        void searchLettersAndInvalidCharacters() {
            // testing with invalid character both on beginning, in middle and end.
            List<String> searchStrings = Arrays.asList("ab!", "!ab", "a!b");

            for(String searchString : searchStrings) {
                for(SearchEnum searchEnum : SearchEnum.values()) {
                    assertNull(postalCodeRegister.search(searchEnum, searchString));
                }
            }
        }
    }
}