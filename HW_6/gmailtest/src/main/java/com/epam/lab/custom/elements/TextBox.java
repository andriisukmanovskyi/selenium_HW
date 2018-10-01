package com.epam.lab.custom.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextBox extends Element {

    public TextBox(WebElement webElement) {
        super(webElement);
    }

    public void typeAndSubmit(String value) {
        webElement.sendKeys(value + Keys.RETURN);

    }
}