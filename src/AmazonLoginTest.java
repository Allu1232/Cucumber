import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonLoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginToAmazon() {
        driver.get("https://www.amazon.com");
        WebElement signInButton = driver.findElement(By.id("nav-link-accountList"));
        signInButton.click();
        WebElement emailField = driver.findElement(By.id("ap_email"));
        emailField.sendKeys("your-email@example.com");
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
        WebElement passwordField = driver.findElement(By.id("ap_password"));
        passwordField.sendKeys("your-password");
        WebElement loginButton = driver.findElement(By.id("signInSubmit"));
        loginButton.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}