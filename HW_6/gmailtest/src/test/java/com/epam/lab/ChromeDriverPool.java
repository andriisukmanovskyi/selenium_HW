package com.epam.lab;

import com.epam.lab.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.epam.lab.utils.Constants.*;

class ChromeDriverPool {

    private static ThreadLocal<WebDriver> chromeDriverPool = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(Utils.getProperties().getProperty(CHROME_DRIVER_TIMEOUT_PROPERTY_KEY)), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    });

    public WebDriver getDriver() {
        return chromeDriverPool.get();
    }

    public void closeDriver() {
        chromeDriverPool.get().quit();
        chromeDriverPool.remove();
    }
}