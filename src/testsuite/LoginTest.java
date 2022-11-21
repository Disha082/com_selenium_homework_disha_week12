package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Base64;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //Find login tab and click on tab
        WebElement loginTab = driver.findElement(By.linkText("Log in"));
        loginTab.click();

        //This is from requirement
        driver.findElement(By.linkText("Log in")).click();

        String expectedMessage = "Welcome, Please Sign In!";

        //Find the welcome text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String actualMessage = actualTextMessageElement.getText();

        //Vlidation actual and expeced
        Assert.assertEquals("Not navigated to Login page", expectedMessage,actualMessage);

    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        driver.findElement(By.linkText("Log in")).click();
        //Located Email field and enterd valid email
        driver.findElement(By.id("Email")).sendKeys("Abc12@gmail.com");
        //Located password field and enter valid credetials
        driver.findElement(By.name("Password")).sendKeys("test1234");

        //After entering valid credetials click on login button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        //Verifying logout text is displayed or no
        String expectedMessae1 = "Log out";
        WebElement actualMessagetext = driver.findElement(By.xpath("//a[text()='Log out']"));
        String actualMessage1 = actualMessagetext.getText();

        Assert.assertEquals("No log out is available", actualMessage1,expectedMessae1);

    }
    @Test
    public void verifyTheErrorMessage(){
        driver.findElement(By.linkText("Log in")).click();
        //Located Email field and entered invalid email
        driver.findElement(By.id("Email")).sendKeys("Abc12@gmail.com");
        //Located Password field and enter invalid credentails
        driver.findElement(By.name("Password")).sendKeys("123458");
        //After entering invalid credentials click on login button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        //Verify login unsuccessful message
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect";

        //Location what actually message it shows when you enter invalid username and password
        WebElement actualMessagetext = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']"));
        String actualMessage = actualMessagetext.getText();

        Assert.assertEquals("No such message is displayed", actualMessage, expectedMessage);
    }

    @After
    public void teardown(){
        closeBrowser();
    }




}


