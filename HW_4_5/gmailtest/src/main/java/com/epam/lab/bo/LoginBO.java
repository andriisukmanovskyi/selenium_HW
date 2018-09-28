package com.epam.lab.bo;

import com.epam.lab.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginBO {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void loginGmail(String login, String password) {
        loginPage.enterLoginAndSubmit(login);
        loginPage.enterPasswordAndSubmit(password);
    }
}