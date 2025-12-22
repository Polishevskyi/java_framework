package utils;

public final class Constants {
    private Constants() {}

    // ==================== Error Messages ====================
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

    // ==================== Products ====================
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_TSHIRT = "Test.allTheThings() T-Shirt";
    public static final String PRODUCT_ONESIE = "Sauce Labs Onesie";
    public static final String PRODUCT_JACKET = "Sauce Labs Fleece Jacket";

    // ==================== UI Elements ====================
    public static final String BUTTON_GO_SHOPPING = "Go Shopping";
    public static final String PRODUCT_PAGE_HEADER_TEXT = "Products";
    public static final String PRODUCT_PAGE_HEADER_NAME = "Products header";

    // ==================== Assertion Messages ====================
    public static final String ASSERT_FIRST_PRODUCT_AFTER_SORTING = "First product name after sorting should be: ";
    public static final String ASSERT_PRODUCT_IN_CART = "Product in cart should be: ";
    public static final String ASSERT_ELEMENT_VISIBLE = "Element should be visible: ";
}
