package com.epam.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmailSendTest {

    private static final String DRIVER_TYPE = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "src/main/resources/webdriver/chromedriver.exe";
    private static final String LOGIN = "tt932101@gmail.com";
    private static final String PASSWORD = "qwertyuiop[]\\";
    private static final String EMAIL_TO = "andriisukmanovskyi@gmail.com";
    private static final String EMAIL_SUBJECT = "test subject";
    private static final String EMAIL_TEXT = "test message content";

    EmailSend emailSend;
    WebDriver driver;

    @BeforeClass
    private void loginGmail() {
        System.setProperty(DRIVER_TYPE, DRIVER_PATH);
        driver = new ChromeDriver();
        emailSend = new EmailSend(driver);
        emailSend.loginGmail(LOGIN, PASSWORD);
    }

    @Test
    public void testSendEmail() {
        Assert.assertTrue(emailSend.sendEmail(EMAIL_TO,
                EMAIL_SUBJECT, EMAIL_TEXT));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}