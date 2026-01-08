package mobile;

import mobile.screens.CartScreen;
import mobile.screens.ProductsScreen;
import org.testng.annotations.Test;
import utils.Constants;

public class CartTest extends BaseMobileTest {
    @Test(description = "Verify that user can add and remove product from cart")
    public void addAndRemoveProductFromCartTest() {
        ProductsScreen productsScreen = new ProductsScreen();
        String productName = productsScreen.getFirstProductName();

        productsScreen.tapOnFirstProduct();
        productsScreen.tapAddToCartButton();

        CartScreen cartScreen = productsScreen.openCart();
        String productNameInCart = cartScreen.getProductNameInCart();
        softly.assertThat(productNameInCart)
                .as(Constants.ASSERT_PRODUCT_IN_CART + productName)
                .isEqualTo(productName);

        cartScreen.tapRemoveItemButton();
        softly.assertThat(cartScreen.getGoShoppingButtonText()).isEqualTo(Constants.BUTTON_GO_SHOPPING);
    }
}
