package web.pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

public class ProductsPage extends BasePage<ProductsPage> {

    private final SelenideElement pageTitle = $(".title");
    private final SelenideElement shoppingCartLink = $(".shopping_cart_link");
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");
    private final SelenideElement burgerMenu = $("#react-burger-menu-btn");
    private final SelenideElement logoutLink = $("#logout_sidebar_link");
    private final SelenideElement addBackpackBtn = $("[data-test='add-to-cart-sauce-labs-backpack']");
    private final SelenideElement addBikeLightBtn = $("[data-test='add-to-cart-sauce-labs-bike-light']");
    private final SelenideElement addBoltTShirtBtn = $("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    private final SelenideElement addFleeceJacketBtn = $("[data-test='add-to-cart-sauce-labs-fleece-jacket']");

    @Override
    public String url() {
        return "/inventory";
    }

    public ProductsPage openMenu() {
        click(burgerMenu);
        return this;
    }

    public LoginPage clickLogout() {
        click(logoutLink);
        return new LoginPage();
    }

    public ProductsPage addBackpackToCart() {
        click(addBackpackBtn);
        return this;
    }

    public ProductsPage addBikeLightToCart() {
        click(addBikeLightBtn);
        return this;
    }

    public ProductsPage addBoltTShirtToCart() {
        click(addBoltTShirtBtn);
        return this;
    }

    public ProductsPage addFleeceJacketToCart() {
        click(addFleeceJacketBtn);
        return this;
    }

    public CartPage openCart() {
        click(shoppingCartLink);
        return new CartPage();
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public String getCartBadgeText() {
        return getText(shoppingCartBadge);
    }

    public boolean isCartBadgeVisible() {
        return isVisible(shoppingCartBadge);
    }
}
