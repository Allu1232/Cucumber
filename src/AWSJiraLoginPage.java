package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.logging.Logger;

public class AWSJiraLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(AWSJiraLoginPage.class.getName());

    @FindBy(id = "txtUsername")
    private WebElement txtUsername;

    @FindBy(id = "txtPassword")
    private WebElement txtPassword;

    @FindBy(id = "btnLogin")
    private WebElement btnLogin;

    @FindBy(xpath = "//div[@class='dashboard-container']")
    private WebElement lblDashboard;

    @FindBy(xpath = "//div[@class='aws-integration-status']")
    private WebElement lblAWSIntegrationStatus;

    @FindBy(xpath = "//div[@class='jira-projects']")
    private WebElement lblJiraProjects;

    @FindBy(xpath = "//nav[@class='main-menu']//a")
    private java.util.List<WebElement> menuItems;

    public AWSJiraLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(String url) {
        logger.info("Navigating to AWS Jira Solution login page: " + url);
        driver.get(url);
    }

    public boolean verifyLoginPageElements() {
        logger.info("Verifying login page elements are visible");
        try {
            wait.until(ExpectedConditions.visibilityOf(txtUsername));
            wait.until(ExpectedConditions.visibilityOf(txtPassword));
            wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
            
            boolean usernameVisible = txtUsername.isDisplayed();
            boolean passwordVisible = txtPassword.isDisplayed();
            boolean loginButtonVisible = btnLogin.isDisplayed();
            
            Assert.assertTrue(usernameVisible && passwordVisible && loginButtonVisible, 
                "Login page elements are not properly displayed");
            
            logger.info("Login page loaded successfully with all required elements");
            return true;
        } catch (Exception e) {
            logger.severe("Failed to verify login page elements: " + e.getMessage());
            return false;
        }
    }

    public void enterCredentialsAndLogin(String username, String password) {
        logger.info("Entering credentials and attempting login");
        try {
            txtUsername.clear();
            txtUsername.sendKeys(username);
            logger.info("Username entered successfully");
            
            txtPassword.clear();
            txtPassword.sendKeys(password);
            logger.info("Password entered successfully");
            
            btnLogin.click();
            logger.info("Login button clicked");
        } catch (Exception e) {
            logger.severe("Failed to enter credentials or click login: " + e.getMessage());
            throw new RuntimeException("Login process failed", e);
        }
    }

    public boolean verifySuccessfulLogin() {
        logger.info("Verifying successful login and dashboard redirect");
        try {
            wait.until(ExpectedConditions.visibilityOf(lblDashboard));
            
            boolean dashboardVisible = lblDashboard.isDisplayed();
            String currentUrl = driver.getCurrentUrl();
            
            Assert.assertTrue(dashboardVisible, "Dashboard is not visible after login");
            Assert.assertTrue(currentUrl.contains("dashboard"), "URL does not indicate dashboard page");
            
            logger.info("User successfully authenticated and redirected to dashboard");
            return true;
        } catch (Exception e) {
            logger.severe("Failed to verify successful login: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyAWSJiraIntegrationComponents() {
        logger.info("Verifying AWS Jira integration components on dashboard");
        try {
            wait.until(ExpectedConditions.visibilityOf(lblAWSIntegrationStatus));
            wait.until(ExpectedConditions.visibilityOf(lblJiraProjects));
            
            boolean awsStatusVisible = lblAWSIntegrationStatus.isDisplayed();
            boolean jiraProjectsVisible = lblJiraProjects.isDisplayed();
            
            Assert.assertTrue(awsStatusVisible, "AWS services integration status is not displayed");
            Assert.assertTrue(jiraProjectsVisible, "Jira project connections are not displayed");
            
            logger.info("Dashboard displays AWS Jira integration components successfully");
            return true;
        } catch (Exception e) {
            logger.severe("Failed to verify AWS Jira integration components: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyMainMenuNavigation() {
        logger.info("Verifying main menu navigation functionality");
        try {
            Assert.assertTrue(menuItems.size() > 0, "No menu items found");
            
            for (int i = 0; i < menuItems.size(); i++) {
                WebElement menuItem = menuItems.get(i);
                String menuText = menuItem.getText();
                
                logger.info("Testing menu item: " + menuText);
                
                menuItem.click();
                Thread.sleep(2000); // Wait for page load
                
                // Verify page loaded without errors
                String pageTitle = driver.getTitle();
                Assert.assertFalse(pageTitle.toLowerCase().contains("error"), 
                    "Error detected in page title for menu: " + menuText);
                
                // Navigate back to dashboard for next menu item
                driver.navigate().back();
                Thread.sleep(1000);
                
                // Refresh menu items list after navigation
                if (i < menuItems.size() - 1) {
                    menuItems = driver.findElements(org.openqa.selenium.By.xpath("//nav[@class='main-menu']//a"));
                }
            }
            
            logger.info("All menu items are accessible and load without errors");
            return true;
        } catch (Exception e) {
            logger.severe("Failed to verify main menu navigation: " + e.getMessage());
            return false;
        }
    }

    public void testAWSJiraSolutionBasicFunctionality() {
        logger.info("Starting AWS Jira Solution basic functionality test");
        
        // Navigate to login page
        navigateToLoginPage("https://aws-jira-solution.example.com/login");
        
        // Verify login page loads with required elements
        Assert.assertTrue(verifyLoginPageElements(), "Login page verification failed");
        
        // Enter credentials and login
        enterCredentialsAndLogin("testuser@example.com", "TestPassword123");
        
        // Verify successful authentication and dashboard redirect
        Assert.assertTrue(verifySuccessfulLogin(), "Login verification failed");
        
        // Verify AWS Jira integration components
        Assert.assertTrue(verifyAWSJiraIntegrationComponents(), "Integration components verification failed");
        
        // Verify main menu navigation
        Assert.assertTrue(verifyMainMenuNavigation(), "Menu navigation verification failed");
        
        logger.info("AWS Jira Solution basic functionality test completed successfully");
    }
}