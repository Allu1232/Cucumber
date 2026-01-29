package com.automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.time.Duration;

public class KairosEpicCreationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://kairos--test.azurewebsites.net/solutionAnalysisInfo/solutionanalysis;organization=Capgemini-Sandbox;portfolio=Performance%20Testing;portfolioId=5124";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testKairosEpicCreation() {
        try {
            // Open website
            driver.get(BASE_URL);
            
            // Wait for page to load
            Thread.sleep(3000);
            
            // Click on Documents
            WebElement documentsElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Documents')]"))
            );
            documentsElement.click();
            
            // Wait for navigation
            Thread.sleep(2000);
            
            // Click on Epics
            WebElement epicsElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Epics')]"))
            );
            epicsElement.click();
            
            // Wait for epics page to load
            Thread.sleep(2000);
            
            // Click on Create Epic Action
            WebElement createEpicElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//body/app-root[1]/app-solution-analysis-info[1]/app-epics[1]/div[1]/div[1]/div[1]"))
            );
            createEpicElement.click();
            
            // Wait for create epic dialog/page to load
            Thread.sleep(2000);
            
            System.out.println("Epic creation test completed successfully");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            throw new RuntimeException("Test execution failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}