package com.epam.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverPool {

    private WebDriver driver;

    public WebDriverPool() {
        this.driver = new ChromeDriver();
    }

    public WebDriver initAndGetDriver(String timeOut) {
        driver.manage().timeouts().implicitlyWait(Integer.valueOf(timeOut), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public void quit() {
        driver.quit();
    }
}