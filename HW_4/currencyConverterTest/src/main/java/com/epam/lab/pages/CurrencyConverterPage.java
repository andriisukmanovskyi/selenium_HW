package com.epam.lab.pages;

import com.epam.lab.currency.type.Currency;
import com.epam.lab.custom.elements.CurrencySelect;
import com.epam.lab.custom.elements.ResultLabel;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverterPage {

    private WebDriver driver;

    @FindBy(name = "currency_from")
    private CurrencySelect currencyFromSelector;

    @FindBy(name = "currency_to")
    private CurrencySelect currencyToSelector;

    @FindBy(name = "amount_from")
    private WebElement inputAmountFrom;

    @FindBy(name = "amount_to")
    private WebElement inputAmountTo;

    @FindBy(xpath = "//div[contains(@id,'ips_uid')]/div[2]/div[2]/div/div[2]/span[1]")
    private WebElement exchangeRateText;

    @FindBy(xpath = "//div[contains(@id,'ips_uid')]/div[2]/div[2]/div/div[1]/span")
    private ResultLabel resultResultLabel;

    public CurrencyConverterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
    }

    public void selectCurrencyFrom(Currency currency) {
        currencyFromSelector.selectByValue(currency.getCurrencyStr());
    }

    public void selectCurrencyTo(Currency currency) {
        currencyToSelector.selectByValue(currency.getCurrencyStr());
    }

    public void enterAmountFrom(double amount) {
        inputAmountFrom.sendKeys(String.valueOf(amount));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterAmountTo(double amount) {
        inputAmountTo.sendKeys(String.valueOf(amount));
    }

    public double getResultAmountFrom() {
        return new BigDecimal(Double.parseDouble(resultResultLabel.getResultAmount())).
                setScale(1, RoundingMode.UP).doubleValue();
    }

    public boolean isCurrencySignRight(Currency currency) {
        return resultResultLabel.getCurrencySign() == currency.getSymbol();
    }

}