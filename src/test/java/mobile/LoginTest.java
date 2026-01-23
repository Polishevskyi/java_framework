package mobile;

import mobile.screens.ProductsScreen;
import mobile.steps.UserSteps;
import org.testng.annotations.Test;
import utils.Constants;

public class LoginTest extends BaseMobileTest {
    @Test(description = "Verify that user can login with valid credentials")
    public void validLoginTest() {
        UserSteps.loginUser();

        ProductsScreen productsScreen = new ProductsScreen();
        softly.assertThat(productsScreen.getProductsHeaderText())
                .as(Constants.ASSERT_ELEMENT_VISIBLE + Constants.PRODUCT_PAGE_HEADER_NAME)
                .isEqualTo(Constants.PRODUCT_PAGE_HEADER_TEXT);
    }
}
