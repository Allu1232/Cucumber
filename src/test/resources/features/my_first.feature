package com.kairos.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class KairosNavigationTest {
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void openKairosWebsite() {
        String url = "https://kairos--test.azurewebsites.net/solutionAnalysisInfo/solutionanalysis;organization=Capgemini-Sandbox;portfolio=Performance%20Testing;portfolioId=5124";
        driver.get(url);
        Assert.assertTrue(driver.getCurrentUrl().contains("kairos--test.azurewebsites.net"));
    }
}