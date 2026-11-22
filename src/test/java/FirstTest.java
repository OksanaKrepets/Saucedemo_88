import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest extends BaseTest {


    @Test
    public void loginLockedUser() {
        driver.findElement(By.name("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[type='submit']")).click();

        boolean errorIsVisible = driver.findElement(By.cssSelector(".error-message-container")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector(".error-message-container")).getText();

        assertTrue(errorIsVisible);
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void correctLogin() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[type='submit']")).click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        assertTrue(currentUrl.contains("inventory.html"));

        boolean pageTitleIsVisible = driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='title']")).getText();

        assertTrue(pageTitleIsVisible);
        assertEquals(errorText, "Products");
    }
}
