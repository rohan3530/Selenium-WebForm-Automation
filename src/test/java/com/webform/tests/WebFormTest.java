package com.webform.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webform.base.BaseTest;
import com.webform.pages.WebFormPage;
import com.webform.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;

public class WebFormTest extends BaseTest {
    // For passing from the Excel !!
     @DataProvider(name = "loginFormData")
    public Object[][] getFormData() {

        return ExcelUtils.getExcelData(
                "TestData/TestData.xlsx",
                "FormData");
    }


    // For passing from belo code !!
    // @DataProvider(name = "loginFormData")
    // public Object[][] getFormData() {
    //     return new Object[][] {
    //         {"Rohan Mali", "Test@123"},
    //         {"Selenium Testing", "Selenium@456"},
    //         {"Automation Testing", "Auto@789"}
    //     };
    // }

     @Test(dataProvider = "loginFormData")
    public void verifyTextAndPasswordInput(String text, String password,String textarea) {

        SoftAssert softAssert = new SoftAssert();

        WebFormPage webFormPage = new WebFormPage(driver);
         System.out.println("Text: " + text);
         System.out.println("Password: " + password);
         System.out.println("TextArea:" + textarea);

        webFormPage.enterText(text);
        webFormPage.enterPassword(password);
        webFormPage.enterTextArea(textarea);

        softAssert.assertEquals(
        webFormPage.getTextFieldValue(),
        text,
        "Text field value does not match!"
    );

      softAssert.assertEquals(
        webFormPage.getPassFieldValue(),
        password,
        "Password field is not getting matched"
    );

       softAssert.assertAll();

    }
}