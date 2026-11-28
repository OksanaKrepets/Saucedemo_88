package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница авторизации
 */

public class LoginPage {
    private final By loginInput = By.cssSelector("#user-name");
    private final By passwordInput = By.id("password");
    private final By submitBtn = By.name("login-button");
    private final By error = By.cssSelector("[data-test='error-button']");
    private final By errorText = By.tagName("h3");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        driver.findElement(loginInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitBtn).click();
    }

    public boolean errorMsgVisible() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorMsgText() {
        return driver.findElement(errorText).getText();
    }
}
