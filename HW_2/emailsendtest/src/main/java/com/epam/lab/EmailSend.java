package com.epam.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class EmailSend {

    private static final String GMAIL_URL = "https://mail.google.com";

    private WebDriver driver;

    public EmailSend(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean sendEmail(String to, String subject, String text) {
        driver.findElement(By.xpath("//*[@role='button' and @class='T-I J-J5-Ji T-I-KE L3']")).click();
        driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(to);
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subject);
        driver.findElement(By.cssSelector("div[role='dialog'] div[role='textbox']")).sendKeys(text);
        driver.findElement(By.xpath("//*[@role='button' and @class='T-I J-J5-Ji aoO T-I-atl L3']")).click();
        try {
            return driver.findElement(By.xpath("//span[contains(text(),'Лист надіслано') or " +
                    "contains(text(),'Письмо отправлено') or contains(text(),'Message sent')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void loginGmail(String userName, String password) {
        driver.get(GMAIL_URL);
        driver.findElement(By.id("identifierId")).sendKeys(userName);
        driver.findElement(By.id("identifierNext")).click();
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.id("passwordNext")).click();
    }
}