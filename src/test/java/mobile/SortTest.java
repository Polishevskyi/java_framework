package mobile;

import mobile.screens.ProductsScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;

public class SortTest extends BaseMobileTest {

    @DataProvider(name = "sortOptions")
    public Object[][] sortOptionsData() {
        return new Object[][] {
            {Constants.SORT_NAME_ASC, Constants.PRODUCT_BACKPACK},
            {Constants.SORT_NAME_DESC, Constants.PRODUCT_TSHIRT},
            {Constants.SORT_PRICE_ASC, Constants.PRODUCT_ONESIE},
            {Constants.SORT_PRICE_DESC, Constants.PRODUCT_JACKET}
        };
    }

    @Test(dataProvider = "sortOptions", description = "Verify that products are sorted correctly")
    public void sortProductsTest(String sortType, String expectedProductName) {
        ProductsScreen productsScreen = new ProductsScreen();

        productsScreen.tapSortButton();

        switch (sortType) {
            case Constants.SORT_NAME_ASC:
                productsScreen.selectNameAscending();
                break;
            case Constants.SORT_NAME_DESC:
                productsScreen.selectNameDescending();
                break;
            case Constants.SORT_PRICE_ASC:
                productsScreen.selectPriceAscending();
                break;
            case Constants.SORT_PRICE_DESC:
                productsScreen.selectPriceDescending();
                break;
            default:
                throw new IllegalArgumentException("Unknown sort type: " + sortType);
        }

        String actualProductName = productsScreen.getFirstProductName();
        softly.assertThat(actualProductName)
                .as(Constants.ASSERT_FIRST_PRODUCT_AFTER_SORTING + expectedProductName)
                .isEqualTo(expectedProductName);
    }
}
