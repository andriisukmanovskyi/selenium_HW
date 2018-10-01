package com.epam.lab;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import com.epam.lab.parser.stax.UsersLoginDataSTAXParser;
import com.epam.lab.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestGmail {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");
    private static final String LOGIN_DATA_FILE_PATH = "src/test/resources/usersLoginData.xml";
    private static final String CHROME_DRIVER_TYPE_PROPERTY_KEY = "chromeDriverType";
    private static final String CHROME_DRIVER_PATH_PROPERTY_KEY = "chromeDriverPath";
    private static final String CHROME_DRIVER_TIMEOUT_PROPERTY_KEY = "chromeDriverTimeOut";

    private String url = "https://mail.google.com";

    private Properties properties;
    private ThreadLocal<WebDriverPool> webDriverPool;

    @DataProvider(name = "loginData", parallel = true)
    private Object[][] loginData() {
        String[][] strings = UsersLoginDataSTAXParser.parseXML(new File(LOGIN_DATA_FILE_PATH));
        return strings;
    }

    @BeforeSuite
    public void logSuiteStart() {
        LOG.info("================== Suite1 START ==================");
        properties = new Properties();
        properties = Utils.readProperties();
        System.setProperty(properties.getProperty(CHROME_DRIVER_TYPE_PROPERTY_KEY), properties.getProperty(CHROME_DRIVER_PATH_PROPERTY_KEY));
        webDriverPool = new ThreadLocal<>();
    }

    @BeforeTest
    public void logTestStart() {
        LOG.info("================== Test1 START ==================");
    }

    @Test(dataProvider = "loginData")
    public void testDeleteEmails(String userName, String password) {
        String expectedMessage = "3 conversations moved to Bin.";
        int emailsCountToMarkAsImportant = 5;
        int emailsCountToDelete = 3;
        webDriverPool.set(new WebDriverPool());
        WebDriver driver = webDriverPool.get().initAndGetDriver(properties.getProperty(CHROME_DRIVER_TIMEOUT_PROPERTY_KEY));
        driver.get(url);
        LoginBO loginBO1 = new LoginBO(driver);
        loginBO1.loginGmail(userName, password);
        GmailBO gmailBO1 = new GmailBO(driver);
        Assert.assertEquals(gmailBO1.markEmailsAsImportant(emailsCountToMarkAsImportant), emailsCountToMarkAsImportant);
        gmailBO1.openImportantFolder();
        Assert.assertEquals(gmailBO1.deleteEmails(emailsCountToDelete), expectedMessage);
        webDriverPool.get().quit();
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