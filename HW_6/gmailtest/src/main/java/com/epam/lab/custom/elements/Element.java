package com.epam.lab.custom.elements;

import org.openqa.selenium.WebElement;

public abstract class Element implements IElement {

    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }
}