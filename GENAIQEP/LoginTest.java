package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginTest {
    
    private WebDriver driver;
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    @FindBy(xpath = "//div[@class='error-message']")
    private WebElement errorMessage;
    
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }
    
    @Test
    public void testInvalidLoginCredentials() {
        // Navigate to login page
        driver.get("https://example.com/login");
        
        // Enter invalid credentials
        usernameField.clear();
        usernameField.sendKeys("invalidUser");
        
        passwordField.clear();
        passwordField.sendKeys("wrongPassword");
        
        // Click login button
        loginButton.click();
        
        // Wait for error message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify error message is displayed
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(errorMessage.getText().contains("invalid") || 
                         errorMessage.getText().contains("Invalid") || 
                         errorMessage.getText().contains("incorrect"),
                         "Error message should indicate invalid credentials");
    }
    
    @Given("I am on the login page")
    public void navigateToLoginPage() {
        driver.get("https://example.com/login");
    }
    
    @When("I enter invalid username {string} and password {string}")
    public void enterInvalidCredentials(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    @When("I click the login button")
    public void clickLoginButton() {
        loginButton.click();
    }
    
    @Then("I should see an error message indicating invalid login credentials")
    public void verifyErrorMessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(errorMessage.getText().contains("invalid") || 
                         errorMessage.getText().contains("Invalid") || 
                         errorMessage.getText().contains("incorrect"),
                         "Error message should indicate invalid credentials");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}