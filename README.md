# Postal Code-register Application
This application is for searching postal codes in Norway.
The information that is stored about a postal code is the
the zip code (postnummer), the city name (poststed)
and the municipality name (kommune).

### File dependency
This application depends on the file "postalcodes.txt".
If this file is not found or unreadable, a new file with the
same name will be created. Please fill it with tab-separated-
postal codes from section 3 of:
[Norwegian Postal Code Tables.](https://www.bring.no/tjenester/adressetjenester/postnummer/postnummertabeller-veiledning
)

## Usage
You can search for a zip code or a city name, with different
modes that can be chosen in the choice box to the right of the 
search field.
The current available modes are:

* ***Starts with:*** Gets all zip codes or cities that start with given text
* ***Ends with:*** Gets all zip codes or cities that end with given text
* ***Contains:*** Gets all zip codes or cities that contain given text
* ***Exact:*** Gets zip code or cities that match the given text exactly

You can only search with numbers (for zip codes)
or unicode letters (for cities).

When you start typing in the search field, the table of postal
codes will automatically update with new matches as you 
are typing.
