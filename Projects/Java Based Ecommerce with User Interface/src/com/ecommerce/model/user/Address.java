package com.ecommerce.model.user;

import com.ecommerce.exception.ValidationException;
import java.util.Objects;

/**
 * Value object representing a physical postal address.
 * Follows encapsulation and immutability principles.
 */
public final class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String country;

    public Address(String street, String city, String state, String zipCode, String country) {
        if (street == null || street.trim().isEmpty()) {
            throw new ValidationException("Street cannot be empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new ValidationException("City cannot be empty");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new ValidationException("State cannot be empty");
        }
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new ValidationException("Zip code cannot be empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new ValidationException("Country cannot be empty");
        }

        this.street = street.trim();
        this.city = city.trim();
        this.state = state.trim();
        this.zipCode = zipCode.trim();
        this.country = country.trim();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getFormattedAddress() {
        return String.format("%s, %s, %s - %s, %s", street, city, state, zipCode, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(street.toLowerCase(), address.street.toLowerCase()) &&
               Objects.equals(city.toLowerCase(), address.city.toLowerCase()) &&
               Objects.equals(state.toLowerCase(), address.state.toLowerCase()) &&
               Objects.equals(zipCode, address.zipCode) &&
               Objects.equals(country.toLowerCase(), address.country.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(street.toLowerCase(), city.toLowerCase(), state.toLowerCase(), zipCode, country.toLowerCase());
    }

    @Override
    public String toString() {
        return getFormattedAddress();
    }
}
