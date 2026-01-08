package utils;

public final class Constants {
    private Constants() {}

    // ==================== API Constants ====================
    // Login validation errors
    public static final String ERROR_USERNAME_REQUIRED = "Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Password is required";
    public static final String ERROR_INVALID_CREDENTIALS =
            "Provided credentials do not match any user in this service.";

    // Configuration errors
    public static final String ERROR_PLATFORM_NULL = "Platform value cannot be null or empty";
    public static final String ERROR_PLATFORM_INVALID = "Invalid platform value: %s. Expected: android or ios";
    public static final String ERROR_ENVIRONMENT_INVALID =
            "Invalid environment value: %s. Expected: true/false or local/cloud";
    public static final String ERROR_LOADING_CONFIG = "Error loading config.properties from project root: %s";
    public static final String ERROR_ACCESSING_CONFIG = "Error accessing config.properties: %s";

    // ==================== Mobile Constants ====================
    // Products
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_TSHIRT = "Test.allTheThings() T-Shirt";
    public static final String PRODUCT_ONESIE = "Sauce Labs Onesie";
    public static final String PRODUCT_JACKET = "Sauce Labs Fleece Jacket";

    // UI Elements
    public static final String BUTTON_GO_SHOPPING = "Go Shopping";
    public static final String PRODUCT_PAGE_HEADER_TEXT = "Products";
    public static final String PRODUCT_PAGE_HEADER_NAME = "Products header";

    // Assertion Messages
    public static final String ASSERT_FIRST_PRODUCT_AFTER_SORTING = "First product name after sorting should be: ";
    public static final String ASSERT_PRODUCT_IN_CART = "Product in cart should be: ";
    public static final String ASSERT_ELEMENT_VISIBLE = "Element should be visible: ";

    // ==================== Web Constants ====================
    // Page Titles
    public static final String PAGE_TITLE_CHECKOUT_INFO = "Checkout: Your Information";
    public static final String PAGE_TITLE_CHECKOUT_OVERVIEW = "Checkout: Overview";
    public static final String PAGE_TITLE_ORDER_COMPLETE = "Thank you for your order!";
    public static final String PAGE_TITLE_PRODUCTS = "Products";
    public static final String PAGE_TITLE_YOUR_CART = "Your Cart";

    // Product Names
    public static final String WEB_PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String WEB_PRODUCT_BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String WEB_PRODUCT_BOLT_TSHIRT = "Sauce Labs Bolt T-Shirt";
    public static final String WEB_PRODUCT_FLEECE_JACKET = "Sauce Labs Fleece Jacket";

    // Cart and Item Counts
    public static final String CART_COUNT_ONE = "1";
    public static final String CART_COUNT_TWO = "2";
    public static final String CART_COUNT_THREE = "3";
    public static final int ITEM_COUNT_TWO = 2;
    public static final int ITEM_COUNT_THREE = 3;

    // Messages
    public static final String MESSAGE_ORDER_DISPATCHED = "Your order has been dispatched";

    // URLs
    public static final String URL_CHECKOUT_COMPLETE = "checkout-complete";

    // Assertion Messages
    public static final String ASSERT_LOGIN_PAGE_AFTER_LOGOUT = "Login page should be displayed after logout";
    public static final String ASSERT_LOGIN_PAGE_DISPLAYED = "Login page should be displayed";
}
