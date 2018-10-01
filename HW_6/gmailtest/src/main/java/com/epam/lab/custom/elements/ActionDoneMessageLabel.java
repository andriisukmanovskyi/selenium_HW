package com.epam.lab.custom.elements;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ActionDoneMessageLabel extends Element {

    public ActionDoneMessageLabel(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        for (int i = 0; i < 30; i++) {
            try {
                webElement.isDisplayed();
                break;
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return webElement.getText();
    }

    public String getConversationsCount() {
        int firstSpaceIndex = webElement.getText().indexOf(" ");
        return webElement.getText().substring(0, firstSpaceIndex);
    }
}