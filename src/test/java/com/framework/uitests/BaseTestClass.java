package com.framework.uitests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import static com.testframework.DriverFactory.getChromedriver;

public class BaseTestClass {

    public static final String BASE_URL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll // @BeforeMethod in TestNG
    static void setUp() {
        driver = getChromedriver();
    }

    @AfterAll
    static void cleanUp() {      // tearDown()
        driver.close();
    }
}
