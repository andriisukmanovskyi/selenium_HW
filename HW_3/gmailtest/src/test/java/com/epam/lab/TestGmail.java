package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestGmail {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/file.properties";

    private WebDriver driver;
    private Properties properties;
    private LoginPage loginPage;
    private GmailHomePage gmailHomePage;

    @BeforeClass
    @Parameters({"url", "username", "password"})
    public void initWebDriver(String url, String userName, String password) {
        properties = new Properties();
        readProperties();
        System.setProperty(properties.getProperty("chromeDriverType"), properties.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("chromeDriverTimeOut")), TimeUnit.SECONDS);
        driver.get(url);
        login(userName, password);
    }

    private void login(String userName, String password) {
        loginPage = new LoginPage(driver);
        loginPage.enterLoginAndSubmit(userName);
        gmailHomePage = loginPage.enterPasswordAndSubmit(password);
        LOG.info("Gmail account is entered successfully");
    }

    private void readProperties() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PROPERTIES_FILE_PATH);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testDeleteEmails() {
        gmailHomePage.markSelectedImportant(driver);
        Assert.assertTrue(gmailHomePage.deleteSelectedEmails(driver));
    }
}