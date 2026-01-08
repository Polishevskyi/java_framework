package web.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class CheckoutPage extends BasePage<CheckoutPage> {

    private final SelenideElement pageTitle = $(".title");
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postalCodeField = $("#postal-code");
    private final SelenideElement continueBtn = $("#continue");
    private final SelenideElement finishBtn = $("#finish");
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement completeText = $(".complete-text");
    private final SelenideElement backHomeBtn = $("#back-to-products");

    @Override
    public String url() {
        return "/checkout-step-one";
    }

    public CheckoutPage fillCheckoutInfo() {
        enterText(firstNameField, FAKER.name().firstName());
        enterText(lastNameField, FAKER.name().lastName());
        enterText(postalCodeField, FAKER.address().zipCode());
        return this;
    }

    public CheckoutPage clickContinue() {
        click(continueBtn);
        return this;
    }

    public CheckoutPage clickFinish() {
        click(finishBtn);
        return this;
    }

    public ProductsPage clickBackHome() {
        click(backHomeBtn);
        return new ProductsPage();
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isFirstNameFieldVisible() {
        return isVisible(firstNameField);
    }

    public boolean isFinishButtonVisible() {
        return isVisible(finishBtn);
    }

    public String getCompleteHeaderText() {
        return getText(completeHeader);
    }

    public String getCompleteText() {
        return getText(completeText);
    }
}
