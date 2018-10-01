package com.epam.lab.bo;

import com.epam.lab.pages.GmailInboxFolder;
import com.epam.lab.pages.GmailImportantFolder;
import org.openqa.selenium.WebDriver;

public class GmailBO {

    private static final String IMPORTANT_FOLDER_SEARCH_VALUE = "is:important";

    private GmailInboxFolder gmailInboxFolder;
    private GmailImportantFolder gmailImportantFolder;

    public GmailBO(WebDriver driver) {
        gmailInboxFolder = new GmailInboxFolder(driver);
    }

    public int markEmailsAsImportant(int emailsCount) {
        gmailInboxFolder.openInboxFolder();
        for (int i = 0; i < emailsCount; i++) {
            gmailInboxFolder.selectEmail(i);
        }
        gmailInboxFolder.openCheckedEmailsMoreOptions();
        gmailInboxFolder.clickImportantItem();
        return Integer.parseInt(gmailInboxFolder.getActionDoneMessageConversationsCount());
    }

    public void openImportantFolder() {
        gmailImportantFolder = gmailInboxFolder.typeAndSubmitSearchMailInput(IMPORTANT_FOLDER_SEARCH_VALUE);
    }

    public String deleteEmails(int emailsCount) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < emailsCount; i++) {
            gmailImportantFolder.selectEmail(i);
        }
        gmailImportantFolder.clickDeleteBtn();
        return gmailImportantFolder.getActionDoneMessageText();
    }
}