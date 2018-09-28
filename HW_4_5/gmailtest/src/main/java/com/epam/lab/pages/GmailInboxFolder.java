package com.epam.lab.pages;

import com.epam.lab.custom.elements.ActionDoneMessageLabel;
import com.epam.lab.custom.elements.TextBox;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

public class GmailInboxFolder {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='aio UKr6le']//span[@class='nU n1']")
    private WebElement inboxFolderBtn;
    @FindBy(xpath = "//div[@role='checkbox']")
    private List<WebElement> inboxEmailCheckerElements;
    @FindBy(xpath = "//div[@role='checkbox']/div")
    private List<WebElement> importantEmailCheckerElements;
    @FindBy(xpath = "//span[@class='asa bjy']")
    private WebElement moreCheckedEmailsOptionsElement;
    @FindBy(xpath = "//div[contains(text(),'Mark as important')]")
    private WebElement importantMarkItem;
    @FindBy(xpath = "//div[@class='vh']/span/span[1]")
    private ActionDoneMessageLabel actionDoneMessageLabel;
    @FindBy(xpath = "//div[@class='aeH']/div[2]/div/div/div/div/div[2]/div[3]")
    private WebElement deleteSelectedBtn;
    @FindBy(xpath = "//input[@name='q']")
    private TextBox seachMailTextBox;

    public GmailInboxFolder(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
        LOG.info("Inbox folder");
    }

    public void openInboxFolder() {
        try {
            inboxFolderBtn.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            inboxFolderBtn.click();
        }
    }

    public void selectEmail(int index) {
        LOG.info("Select " + index + " email");
        inboxEmailCheckerElements.get(index).click();
    }

    public void openCheckedEmailsMoreOptions() {
        LOG.info("Open more options for selected emails");
        moreCheckedEmailsOptionsElement.click();
    }

    public void clickImportantItem() {
        LOG.info("Click on \"Mark as important\" item");
        importantMarkItem.click();
    }

    public String getActionDoneMessageText() {
        LOG.info("Get message about successful marked to important operation");
        return actionDoneMessageLabel.getText();
    }

    public String getActionDoneMessageConversationsCount() {
        LOG.info("Get marked conversations count");
        return actionDoneMessageLabel.getConversationsCount();
    }

    public GmailImportantFolder typeAndSubmitSearchMailInput(String value) {
        LOG.info("Navigate to \"Important folder\" using search input");
        seachMailTextBox.typeAndSubmit(value);
        return new GmailImportantFolder(driver);
    }
}