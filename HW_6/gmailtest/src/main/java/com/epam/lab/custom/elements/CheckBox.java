package com.epam.lab.custom.elements;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class CheckBox extends Element{

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public void clickIfAtached(){
        for (int i = 0; i < 30; i++) {
            try {
                webElement.click();
                break;
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}