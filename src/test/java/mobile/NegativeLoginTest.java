package mobile;

import mobile.screens.LoginScreen;
import mobile.screens.MenuScreen;
import mobile.screens.ProductsScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.Constants;

public class NegativeLoginTest extends BaseMobileTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"", ConfigReader.getProperty("test.credentials.password"), Constants.ERROR_USERNAME_REQUIRED},
            {ConfigReader.getProperty("test.credentials.username"), "", Constants.ERROR_PASSWORD_REQUIRED},
            {"", "", Constants.ERROR_USERNAME_REQUIRED},
            {
                FAKER.internet().emailAddress(),
                ConfigReader.getProperty("test.credentials.password"),
                Constants.ERROR_INVALID_CREDENTIALS
            },
            {
                ConfigReader.getProperty("test.credentials.username"),
                FAKER.internet().password(),
                Constants.ERROR_INVALID_CREDENTIALS
            },
            {FAKER.internet().emailAddress(), FAKER.internet().password(), Constants.ERROR_INVALID_CREDENTIALS}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Verify that user cannot login with invalid credentials")
    public void invalidLoginTest(String username, String password, String expectedError) {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(username);
        loginScreen.enterPassword(password);
        loginScreen.tapLoginButtonExpectingError();

        String actualError = "";
        if (expectedError.equals(Constants.ERROR_USERNAME_REQUIRED)) {
            actualError = loginScreen.getUserNameErrorText();
        } else if (expectedError.equals(Constants.ERROR_PASSWORD_REQUIRED)) {
            actualError = loginScreen.getPasswordErrorText();
        } else if (expectedError.equals(Constants.ERROR_INVALID_CREDENTIALS)) {
            actualError = loginScreen.getCredentialsErrorText();
        }

        softly.assertThat(actualError).isEqualTo(expectedError);
    }
}
