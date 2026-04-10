package mobile;

import mobile.screens.LoginScreen;
import mobile.screens.MenuScreen;
import mobile.screens.ProductsScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;

public class NegativeLoginTest extends BaseMobileTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"", FAKER.internet().password(), Constants.ERROR_USERNAME_REQUIRED},
            {FAKER.internet().emailAddress(), "", Constants.ERROR_PASSWORD_REQUIRED},
            {"", "", Constants.ERROR_USERNAME_REQUIRED},
            {FAKER.internet().emailAddress(), FAKER.internet().password(), Constants.ERROR_INVALID_CREDENTIALS}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Verify that user cannot login with invalid credentials")
    public void invalidLoginTest(String username, String password, String expectedError) {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(username);
        loginScreen.enterPassword(password);
        loginScreen.tapLoginButton();

        String actualError;
        switch (expectedError) {
            case Constants.ERROR_USERNAME_REQUIRED:
                actualError = loginScreen.getUserNameErrorText();
                break;
            case Constants.ERROR_PASSWORD_REQUIRED:
                actualError = loginScreen.getPasswordErrorText();
                break;
            case Constants.ERROR_INVALID_CREDENTIALS:
                actualError = loginScreen.getCredentialsErrorText();
                break;
            default:
                throw new IllegalArgumentException("Unknown error type: " + expectedError);
        }

        softly.assertThat(actualError).isEqualTo(expectedError);
    }
}
