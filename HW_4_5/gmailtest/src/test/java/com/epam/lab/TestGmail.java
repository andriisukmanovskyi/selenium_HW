package com.epam.lab;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestGmail {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/file.properties";

    private WebDriver driver;
    private Properties properties;
    private LoginBO loginBO;
    private GmailBO gmailBO;

    @BeforeClass
    @Parameters({"url", "username", "password"})
    public void initWebDriver(String url, String userName, String password) {
        properties = new Properties();
        readProperties();
        System.setProperty(properties.getProperty("chromeDriverType"), properties.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("chromeDriverTimeOut")), TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        loginBO = new LoginBO(driver);
        loginBO.loginGmail(userName, password);
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

    @BeforeSuite
    public void logSuiteStart() {
        LOG.info("================== Suite1 START ==================");
    }

    @BeforeTest
    public void logTestStart() {
        LOG.info("================== Test1 START ==================");
    }

    @Test
    public void testDeleteEmails() {
        gmailBO = new GmailBO(driver);
        Assert.assertEquals(gmailBO.markEmailsAsImportant(5), 5);
        gmailBO.openImportantFolder();
        Assert.assertEquals(gmailBO.deleteEmails(4), "4 conversations moved to Bin.");
    }

    @AfterTest
    public void logTestEnd() {
        LOG.info("================== Test1 END ==================");
    }

    @AfterSuite
    public void logSuiteEnd() {
        LOG.info("================== Suite1 END ==================\n");
    }
}