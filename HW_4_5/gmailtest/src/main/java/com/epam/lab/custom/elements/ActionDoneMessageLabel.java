package com.epam.lab.custom.elements;

import org.openqa.selenium.WebElement;

public class ActionDoneMessageLabel extends Element {

    public ActionDoneMessageLabel(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }

    public String getConversationsCount() {
        int firstSpaceIndex = webElement.getText().indexOf(" ");
        return webElement.getText().substring(0, firstSpaceIndex);
    }
}