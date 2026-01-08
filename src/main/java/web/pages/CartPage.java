package web.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;

public class CartPage extends BasePage<CartPage> {

    private final SelenideElement pageTitle = $(".title");
    private final SelenideElement continueShoppingBtn = $("#continue-shopping");
    private final SelenideElement checkoutBtn = $("#checkout");
    private final SelenideElement removeBackpackBtn = $("[data-test='remove-sauce-labs-backpack']");
    private final SelenideElement removeBikeLightBtn = $("[data-test='remove-sauce-labs-bike-light']");
    private final SelenideElement removeBoltTShirtBtn = $("[data-test='remove-sauce-labs-bolt-t-shirt']");
    private final SelenideElement removeFleeceJacketBtn = $("[data-test='remove-sauce-labs-fleece-jacket']");
    private final ElementsCollection cartItems = $$(".cart_item");
    private final ElementsCollection cartItemNames = $$(".inventory_item_name");

    @Override
    public String url() {
        return "/cart";
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getCartItemsCount() {
        return getCount(cartItems);
    }

    public List<String> getCartItemNames() {
        return getAllTexts(cartItemNames);
    }

    public CartPage removeBackpack() {
        click(removeBackpackBtn);
        return this;
    }

    public CartPage removeBikeLight() {
        click(removeBikeLightBtn);
        return this;
    }

    public CartPage removeBoltTShirt() {
        click(removeBoltTShirtBtn);
        return this;
    }

    public CartPage removeFleeceJacket() {
        click(removeFleeceJacketBtn);
        return this;
    }

    public ProductsPage clickContinueShopping() {
        click(continueShoppingBtn);
        return new ProductsPage();
    }

    public CheckoutPage clickCheckout() {
        click(checkoutBtn);
        return new CheckoutPage();
    }
}
