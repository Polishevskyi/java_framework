package web;

import org.testng.annotations.Test;
import utils.Constants;
import web.pages.CheckoutPage;
import web.pages.ProductsPage;
import web.steps.UserSteps;

public class CheckoutTest extends BaseWebTest {
    @Test(description = "Verify that user can complete checkout process with generated user data")
    public void completeCheckoutWithGeneratedUserDataTest() {
        ProductsPage productsPage = UserSteps.loginUser();
        softly.assertThat(productsPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_PRODUCTS);

        productsPage.addBackpackToCart().addFleeceJacketToCart();
        softly.assertThat(productsPage.getCartBadgeText()).isEqualTo(Constants.CART_COUNT_TWO);

        CheckoutPage checkoutPage = productsPage.openCart().clickCheckout();
        softly.assertThat(checkoutPage.isFirstNameFieldVisible()).isTrue();
        softly.assertThat(checkoutPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_CHECKOUT_INFO);

        checkoutPage.fillCheckoutInfo().clickContinue();
        softly.assertThat(checkoutPage.isFinishButtonVisible()).isTrue();
        softly.assertThat(checkoutPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_CHECKOUT_OVERVIEW);

        checkoutPage.clickFinish().waitForUrl(Constants.URL_CHECKOUT_COMPLETE);
        softly.assertThat(checkoutPage.getCompleteHeaderText()).isEqualTo(Constants.PAGE_TITLE_ORDER_COMPLETE);
        softly.assertThat(checkoutPage.getCompleteText()).contains(Constants.MESSAGE_ORDER_DISPATCHED);

        productsPage = checkoutPage.clickBackHome();
        softly.assertThat(productsPage.getPageTitle()).isEqualTo(Constants.PAGE_TITLE_PRODUCTS);
        softly.assertThat(productsPage.isCartBadgeVisible()).isFalse();
    }
}
