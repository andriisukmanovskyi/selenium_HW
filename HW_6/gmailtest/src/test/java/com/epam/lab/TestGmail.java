package com.epam.lab;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import com.epam.lab.parser.stax.UsersLoginDataSTAXParser;
import com.epam.lab.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.Properties;

import static com.epam.lab.utils.Constants.*;

public class TestGmail {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    private String url;

    private Properties properties;
    private ChromeDriverPool chromeDriverPool;

    @DataProvider(name = "loginData", parallel = true)
    private Object[][] loginData() {
        String[][] strings = UsersLoginDataSTAXParser.parseXML(new File(LOGIN_DATA_FILE_PATH));
        return strings;
    }

    @BeforeSuite
    @Parameters("url")
    public void init(String url) {
        LOG.info("================== Suite1 START ==================");
        this.url = url;
        properties = new Properties();
        chromeDriverPool = new ChromeDriverPool();
        properties = Utils.getProperties();
        System.setProperty(properties.getProperty(CHROME_DRIVER_TYPE_PROPERTY_KEY), properties.getProperty(CHROME_DRIVER_PATH_PROPERTY_KEY));
    }

    @Test(dataProvider = "loginData")
    public void testDeleteEmails(String userName, String password) {
        String expectedMessage = "3 conversations moved to Bin.";
        int emailsCountToMarkAsImportant = 5;
        int emailsCountToDelete = 3;
        WebDriver driver = chromeDriverPool.getDriver();
        driver.get(url);
        LoginBO loginBO = new LoginBO(driver);
        loginBO.loginGmail(userName, password);
        GmailBO gmailBO = new GmailBO(driver);
        Assert.assertEquals(gmailBO.markEmailsAsImportant(emailsCountToMarkAsImportant), emailsCountToMarkAsImportant);
        gmailBO.openImportantFolder();
        Assert.assertEquals(gmailBO.deleteEmails(emailsCountToDelete), expectedMessage);
        chromeDriverPool.closeDriver();
    }

    @BeforeTest
    public void logTestStart() {
        LOG.info("================== Test1 START ==================");
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