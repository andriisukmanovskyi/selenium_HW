package com.epam.lab.custom.elements;

import org.openqa.selenium.WebElement;

public class ResultLabel {

    private WebElement webElement;

    public ResultLabel(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getResultAmount() {
        String resultStr = webElement.getText();
        resultStr = resultStr.replace(",", ".");
        resultStr = resultStr.replace(" ", "");
        return resultStr.substring(0, resultStr.length() - 1);
    }

    public char getCurrencySign() {
        return webElement.getText().charAt(webElement.getText().length() - 1);
    }
}