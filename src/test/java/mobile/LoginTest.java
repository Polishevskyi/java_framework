package mobile;

import mobile.screens.LoginScreen;
import mobile.screens.MenuScreen;
import mobile.screens.ProductsScreen;
import org.testng.annotations.Test;
import utils.Constants;
import utils.ProjectConfig;

public class LoginTest extends BaseMobileTest {
    @Test(description = "Verify that user can login with valid credentials")
    public void validLoginTest() {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(ProjectConfig.CONFIG.getMobileCredentialsUsername());
        loginScreen.enterPassword(ProjectConfig.CONFIG.getMobileCredentialsPassword());
        loginScreen.tapLoginButton();

        ProductsScreen productsScreen = new ProductsScreen();
        softly.assertThat(productsScreen.getProductsHeaderText())
                .as(Constants.ASSERT_ELEMENT_VISIBLE + Constants.PRODUCT_PAGE_HEADER_NAME)
                .isEqualTo(Constants.PRODUCT_PAGE_HEADER_TEXT);
    }
}
