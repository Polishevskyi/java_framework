package web.pages;

import static com.codeborne.selenide.Selenide.Wait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import java.util.List;

public abstract class BasePage<T extends BasePage<T>> {

    protected static final Faker FAKER = new Faker();

    public abstract String url();

    @SuppressWarnings("unchecked")
    public T open() {
        return Allure.step("Open page: " + url(), () -> {
            return Selenide.open(url(), (Class<T>) this.getClass());
        });
    }

    public void waitForUrl(String urlPart) {
        Wait().until(driver -> driver.getCurrentUrl().contains(urlPart));
    }

    protected void enterText(SelenideElement element, String text) {
        Allure.step("Enter text '" + text + "' into field: " + element, () -> {
            element.clear();
            element.setValue(text);
        });
    }

    protected void click(SelenideElement element) {
        Allure.step("Click on element: " + element, () -> {
            element.shouldBe(Condition.visible).click();
        });
    }

    protected String getText(SelenideElement element) {
        return Allure.step("Get text from element: " + element, () -> {
            return element.shouldBe(Condition.visible).getText();
        });
    }

    protected boolean isVisible(SelenideElement element) {
        return Allure.step("Verify element is visible: " + element, () -> {
            return element.is(Condition.visible);
        });
    }

    protected int getCount(ElementsCollection elements) {
        return elements.size();
    }

    protected List<String> getAllTexts(ElementsCollection elements) {
        return elements.texts();
    }
}
