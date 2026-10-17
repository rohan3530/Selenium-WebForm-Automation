
package com.webform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormPage {

    private WebDriver driver;

    // Constructor
    public WebFormPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By textInput = By.name("my-text");
    private By passwordInput = By.name("my-password");
    private By textArea= By.name("my-textarea");
    // private By submitButton = By.cssSelector("button");

    // Page actions
    public void enterText(String text) {
        driver.findElement(textInput).sendKeys(text);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterTextArea(String text)
    {
        driver.findElement(textArea).sendKeys(text);
    }

    // public void clickSubmit() {
    //     driver.findElement(submitButton).click();
    // }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
