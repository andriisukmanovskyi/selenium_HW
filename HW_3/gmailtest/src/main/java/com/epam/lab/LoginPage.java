package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    @FindBy(id = "identifierId")
    private WebElement loginInput;
    @FindBy(id = "identifierNext")
    private WebElement nextButton;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;
    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLoginAndSubmit(String login) {
        loginInput.sendKeys(login);
        nextButton.click();
        LOG.info("Login is entered successfully");
    }

    public GmailHomePage enterPasswordAndSubmit(String password) {
        passwordInput.sendKeys(password);
        passwordNextButton.click();
        LOG.info("Password is entered successfully");
        return new GmailHomePage(driver);
    }
}