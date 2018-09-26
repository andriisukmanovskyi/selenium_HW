package com.epam.lab;

import com.epam.lab.currency.type.Currency;
import com.epam.lab.pages.CurrencyConverterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CurrencyConverterTest {

    private static final String PROPERTIES_FILE_PATH = "src/main/resources/file.properties";

    private WebDriver driver;
    private CurrencyConverterPage currencyConverterPage;
    private Properties properties;

    @BeforeClass
    @Parameters({"url"})
    public void initDriver(String url) {
        properties = new Properties();
        readProperties();
        System.setProperty(properties.getProperty("chromeDriverType"), properties.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("chromeDriverTimeOut")), TimeUnit.SECONDS);
        driver.get(url);
        currencyConverterPage = new CurrencyConverterPage(driver);
    }

    @Test
    public void testCurrencySymbol() {
        currencyConverterPage.selectCurrencyTo(Currency.USD);
        Assert.assertTrue(currencyConverterPage.isCurrencySignRight(Currency.USD));
    }

    @Test
    public void testConvertedAmount() {
        double inputAmount = 20;
        double exchangeRate = 0.8943;
        double expectedResult = new BigDecimal(inputAmount * exchangeRate).setScale(1, RoundingMode.UP).doubleValue();
        currencyConverterPage.selectCurrencyFrom(Currency.EUR);
        currencyConverterPage.selectCurrencyTo(Currency.GBP);
        currencyConverterPage.enterAmountFrom(inputAmount);
        Assert.assertEquals(currencyConverterPage.getResultAmountFrom(), expectedResult);
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
}