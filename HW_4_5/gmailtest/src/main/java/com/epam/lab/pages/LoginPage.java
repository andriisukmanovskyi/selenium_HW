package com.epam.lab.pages;

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
        LOG.info("Login page");
    }

    public void enterLoginAndSubmit(String login) {
        LOG.info("Enter login and submit");
        loginInput.sendKeys(login);
        nextButton.click();
    }

    public void enterPasswordAndSubmit(String password) {
        LOG.info("Enter password and submit");
        passwordInput.sendKeys(password);
        passwordNextButton.click();
    }
}