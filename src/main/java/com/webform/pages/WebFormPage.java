
package com.webform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
    private By dropdown = By.name("my-select");
    // private By submitButton = By.cssSelector("button");

    // Page actions
    public void enterText(String text) {
        driver.findElement(textInput).sendKeys(text);
    }
     // Get actual text field value
    public String getTextFieldValue() {
        return driver.findElement(textInput).getAttribute("value");
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    // Get actual text field value
    public String getPassFieldValue() {
        return driver.findElement(passwordInput).getAttribute("value");
    }

    public void enterTextArea(String text)
    {
        driver.findElement(textArea).sendKeys(text);
    }

    public String getTextArea(){
        return driver.findElement(textArea).getAttribute("value");
    }

    public void selectDropdownByVisibleText(String option) {

    WebElement element = driver.findElement(dropdown);

    Select select = new Select(element);

    select.selectByVisibleText(option);
   
}

// Get the currently selected dropdown option
public String getSelectedDropdownOption() {

    WebElement element = driver.findElement(dropdown);

    Select select = new Select(element);

    return select.getFirstSelectedOption().getText();
}

    // public void clickSubmit() {
    //     driver.findElement(submitButton).click();
    // }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
