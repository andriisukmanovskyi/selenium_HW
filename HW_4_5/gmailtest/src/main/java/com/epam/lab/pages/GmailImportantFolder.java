package com.epam.lab.pages;

import com.epam.lab.custom.elements.ActionDoneMessageLabel;
import com.epam.lab.custom.elements.Button;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GmailImportantFolder {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    private WebDriver driver;

    @FindBy(xpath = "//div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr/td[2]/div")
    private List<WebElement> importantEmailCheckerElements;
    @FindBy(xpath = "//div[@class='vh']/span/span[1]")
    private ActionDoneMessageLabel actionDoneMessageLabel;
    @FindBy(xpath = "//div[@class='aeH']/div[2]/div/div/div/div/div[2]/div[3]")
    private Button deleteSelectedBtn;

    public GmailImportantFolder(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
        LOG.info("Important folder is opened");
    }

    public void selectEmail(int index) {
        try {
            LOG.info("Select " + index + " email");
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(10, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(StaleElementReferenceException.class);
            WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOf(importantEmailCheckerElements.get(index)));
            element.click();
        } catch (StaleElementReferenceException e) {
            selectEmail(index);
        }
    }

    public String getActionDoneMessageText() {
        LOG.info("Get message about successful delete operation");
        return actionDoneMessageLabel.getText();
    }

    public String getActionDoneMessageConversationsCount() {
        LOG.info("Get deleted conversations count");
        return actionDoneMessageLabel.getConversationsCount();
    }

    public void clickDeleteBtn() {
        LOG.info("Click \"Delete\" icon");
        deleteSelectedBtn.click();
    }
}