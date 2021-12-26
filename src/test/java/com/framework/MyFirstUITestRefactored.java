package com.framework;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class MyFirstUITestRefactored {

    public static final String BASE_URL = "https://github.com/";
    WebDriver driver;

    @BeforeEach // @BeforeMethod in TestNG
     void setUp() {
         // Create the driver object

         // Arrange
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    void cleanUp() {      // tearDown()
        driver.close();
    }


    @Test
    void userNameIsCorrectOnOverviewTab() {

        //Arrange
        String user = "andrejs-ps";
        driver.get(BASE_URL + user);

        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        assertEquals(user, actualUserName);

    }



    @Test
    void repoLinkGoesToCorrectRepo() {

        String user = "andrejs-ps";
        driver.get(BASE_URL + user);

        //Act

        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualUrl = driver.getCurrentUrl();

        //Assert
        assertEquals(BASE_URL + "andrejs-ps/" + repo , actualUrl);

    }

    @Test
    void repositoryCountIsCorrect() {
        //Act
        driver.get(BASE_URL + "andrejs-ps/" + "?tab=repositories");

        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));

//        String expectedRepoSizeString = driver.findElement(By.cssSelector("span.Counter")).getText();
//        Integer expectedRepoSizeINT = Integer.parseInt(expectedRepoSizeString);

        //Assert

//        Assertions.assertEquals(expectedRepoSizeINT , repos.size());
        assertEquals(9 , repos.size());

    }



}



