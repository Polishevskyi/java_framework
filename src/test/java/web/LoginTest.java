package web;

import org.testng.annotations.Test;
import utils.Constants;
import web.pages.LoginPage;
import web.pages.ProductsPage;
import web.steps.UserSteps;

public class LoginTest extends BaseWebTest {
    @Test(description = "Verify that user can successfully login and logout")
    public void successfulLoginAndLogoutTest() {
        LoginPage loginPage = new LoginPage().open();
        softly.assertThat(loginPage.isLoginPageDisplayed())
                .as(Constants.ASSERT_LOGIN_PAGE_DISPLAYED)
                .isTrue();

        ProductsPage productsPage = UserSteps.loginUser();
        softly.assertThat(productsPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_PRODUCTS);

        loginPage = productsPage.openMenu().clickLogout();
        softly.assertThat(loginPage.isLoginPageDisplayed())
                .as(Constants.ASSERT_LOGIN_PAGE_AFTER_LOGOUT)
                .isTrue();
    }
}
