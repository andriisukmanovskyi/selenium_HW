package com.epam.lab.pages;

import com.epam.lab.custom.elements.TextBox;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
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
    private TextBox passwordInput;
    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
        LOG.info("Login page " + Thread.currentThread().getId());
    }

    public void enterLoginAndSubmit(String login) {
        LOG.info("Enter login and submit " + Thread.currentThread().getId());
        loginInput.sendKeys(login);
        nextButton.click();
    }

    public void enterPasswordAndSubmit(String password) {
        LOG.info("Enter password and submit " + Thread.currentThread().getId());
        passwordInput.typeAndSubmit(password);
    }
}