package com.epam.lab.custom.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CurrencySelect {

    private WebElement element;

    public CurrencySelect(WebElement webElement) {
        this.element = webElement;
    }

    public void selectByValue(String value) {
        List<WebElement> options = element.findElements(By.xpath(
                "//select[@name='" + element.getAttribute("name") + "']/optgroup[1]/option"));
        boolean matched = false;
        for (WebElement option : options) {
            if (option.getText().contains(value)) {
                setSelected(option, true);
                matched = true;
            }
        }
        if (!matched) {
            throw new NoSuchElementException("Cannot locate option with value: " + value);
        }
    }

    private void setSelected(WebElement option, boolean select) {
        boolean isSelected = option.isSelected();
        if (!isSelected && select || isSelected && !select) {
            option.click();
        }
    }
}