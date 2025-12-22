package mobile;

import mobile.screens.CartScreen;
import mobile.screens.ProductsScreen;
import org.testng.Assert;
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
        Assert.assertEquals(productNameInCart, productName, Constants.ASSERT_PRODUCT_IN_CART + productName);

        cartScreen.tapRemoveItemButton();
        Assert.assertEquals(cartScreen.getGoShoppingButtonText(), Constants.BUTTON_GO_SHOPPING);
    }
}
