package com.parabank.data;

public final class TestDataFactory {

    private static final String PASSWORD = "Password123!";

    public static NewUser uniqueUser() {
        String suffix = String.valueOf(System.currentTimeMillis());
        return new NewUser(
                "Daria",
                "Test",
                "Main street 1",
                "Minsk",
                "Minsk",
                "220000",
                "375291234567",
                "123456789",
                "user_" + suffix,
                PASSWORD
        );
    }

    private TestDataFactory() {
    }
}