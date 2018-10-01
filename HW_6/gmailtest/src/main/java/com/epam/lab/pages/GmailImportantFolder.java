package com.epam.lab.pages;

import com.epam.lab.custom.elements.ActionDoneMessageLabel;
import com.epam.lab.custom.elements.Button;
import com.epam.lab.custom.elements.CheckBox;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GmailImportantFolder {

    private static final Logger LOG = LogManager.getLogger("com.epam.lab");

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='AO']/div/div/div[@class='nH']/div[2]//tbody[1]/tr/td[2]/div")
    private List<CheckBox> importantEmailCheckerElements;
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
        LOG.info("Select " + index + " email");
        importantEmailCheckerElements.get(index).clickIfAtached();
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