package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailHomePage {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    private WebElement mailCheckerElement;
    @FindBy(xpath = "//div[contains(@data-tooltip,'Inbox')]")
    private WebElement inboxFolderBtn;
    @FindBy(xpath = "//span[@class='asa bjy']")
    private WebElement moreOptionsElement;
    @FindBy(xpath = "//div[@role='menuitem']/div[contains(text(), 'as important')]")
    private WebElement importantMarkBtn;
    @FindBy(xpath = "//div[@class='vh']/span[@class='aT']/span[1]")
    private WebElement messageElement;
    @FindBy(xpath = "//div[@data-tooltip='Delete']/div[@class='asa']/div[@class='ar9 T-I-J3 J-J5-Ji']")
    private WebElement deleteSelectedBtn;

    private int checkedMailCount = 3;
    private String[] xPathStr = new String[]{
            "//div[@class='Cp']//tbody/tr[",
            "]//div[@role='checkbox']"
    };

    public GmailHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean markSelectedImportant(WebDriver driver) {
        selectEmails(driver);
        moreOptionsElement.click();
        importantMarkBtn.click();
        if(isActionSuccessful(checkedMailCount + " conversations marked as not important")){
            LOG.info("Emails are successfully marked as important");
            return true;
        }
        return false;
    }

    private boolean isActionSuccessful(String expectedMessage) {
        try {
            return messageElement.getText().contains(expectedMessage);
        } catch (Exception e) {
            return false;
        }
    }

    private void selectEmails(WebDriver driver) {
        try {
            inboxFolderBtn.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            inboxFolderBtn.click();
        }
        for (int i = 1; i <= checkedMailCount; i++) {
            driver.findElement(By.xpath(xPathStr[0] + i + xPathStr[1])).click();
        }
    }

    public boolean deleteSelectedEmails(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(deleteSelectedBtn).click().perform();
        if (isActionSuccessful(checkedMailCount + " conversations moved to Bin")) {
            LOG.info("Emails are successfully deleted");
            return true;
        }
        return false;
    }
}