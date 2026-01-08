package web;

import java.util.List;
import org.testng.annotations.Test;
import utils.Constants;
import web.pages.CartPage;
import web.pages.ProductsPage;
import web.steps.UserSteps;

public class ShoppingTest extends BaseWebTest {
    @Test(description = "Verify that user can add multiple products to cart and remove one")
    public void addMultipleProductsAndRemoveOneTest() {
        ProductsPage productsPage = UserSteps.loginUser();
        softly.assertThat(productsPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_PRODUCTS);

        productsPage.addBackpackToCart().addBikeLightToCart().addBoltTShirtToCart();
        softly.assertThat(productsPage.getCartBadgeText()).isEqualTo(Constants.CART_COUNT_THREE);

        CartPage cartPage = productsPage.openCart();
        softly.assertThat(cartPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_YOUR_CART);
        softly.assertThat(cartPage.getCartItemsCount()).isEqualTo(Constants.ITEM_COUNT_THREE);

        List<String> items = cartPage.getCartItemNames();
        softly.assertThat(items).contains(Constants.WEB_PRODUCT_BACKPACK);
        softly.assertThat(items).contains(Constants.WEB_PRODUCT_BIKE_LIGHT);
        softly.assertThat(items).contains(Constants.WEB_PRODUCT_BOLT_TSHIRT);

        cartPage.removeBikeLight();
        softly.assertThat(cartPage.getCartItemsCount()).isEqualTo(Constants.ITEM_COUNT_TWO);

        productsPage = cartPage.clickContinueShopping();
        softly.assertThat(productsPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_PRODUCTS);
        softly.assertThat(productsPage.getCartBadgeText()).isEqualTo(Constants.CART_COUNT_TWO);
    }
}
