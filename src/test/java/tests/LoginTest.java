package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void invalidUserLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void noPasswordLogin() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Password is required");
    }

    @Test
    public void noLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username is required");
    }

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"));
        assertTrue(productsPage.pageTitleVisible());
        assertEquals(productsPage.getTitleText(), "Products");
    }
}
