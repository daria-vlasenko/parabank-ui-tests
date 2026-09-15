package com.parabank.config;

public final class WebConfig {

    public static final String BASE_URL = "https://parabank.parasoft.com/parabank";

    public static final long TIMEOUT_MS = 10_000;

    public static final String BROWSER_SIZE = "1920x1080";

    public static final String BROWSER = System.getProperty("browser", "chrome");

    private WebConfig() {
    }
}