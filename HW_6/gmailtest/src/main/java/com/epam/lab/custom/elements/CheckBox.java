package com.epam.lab.custom.elements;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class CheckBox extends Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public void clickIfAtached() {
        webElement.click();
    }
}