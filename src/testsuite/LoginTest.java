package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class LoginTest extends Utility {

    String baseUrl = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        sendTextElement(By.id("user-name"), "standard_user");   //Enter User Name
        sendTextElement(By.id("password"), "secret_sauce");    // Enter Password
        clickOnElement(By.id("login-button"));    // Click on Login

        String expectedText = "Products";   // Verify the Text
        String actualText = getTextFromElement(By.xpath("//span[text()='Products']"));
        Assert.assertEquals("Text is not Displayed", expectedText, actualText);
    }

    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {
        sendTextElement(By.id("user-name"), "standard_user");   // Enter User Name
        sendTextElement(By.id("password"), "secret_sauce");     // Enter Password
        clickOnElement(By.id("login-button"));  // Click on Login

        int expectedNumberOfProducts = 6;   // Verify the 6 Products using List method
        List<WebElement> productList = findElementsFromWebPage(By.xpath("//img[starts-with(@class,'inventory_item_img')]"));
        int actualNumberOfProducts = productList.size();
        Assert.assertEquals("Number of Products are not same", expectedNumberOfProducts, actualNumberOfProducts);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
