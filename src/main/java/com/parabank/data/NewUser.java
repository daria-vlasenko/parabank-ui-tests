package com.parabank.data;

public record NewUser(
        String firstName,
        String lastName,
        String street,
        String city,
        String state,
        String zipCode,
        String phone,
        String ssn,
        String username,
        String password
) {
}