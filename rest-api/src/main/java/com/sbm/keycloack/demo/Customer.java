package com.sbm.keycloack.demo;

import lombok.Value;

/**
 * @author mohamed.elhosni
 */
@Value
class Customer {
    private final Long id;
    private final String city;
    private final String name;
    private final String street;
    private final String zip;
    private final String country;
}
