# 🚀 Mobile, Web & API Test Automation Framework

**Comprehensive test automation framework for API, Web, and Mobile testing with Java, Appium, REST Assured, Selenide, TestNG, and Allure reporting**

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg)](https://testng.org/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.5.1-green.svg)](https://rest-assured.io/)
[![Jackson](https://img.shields.io/badge/Jackson-2.19.0-blue.svg)](https://github.com/FasterXML/jackson)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.3-orange.svg)](https://assertj.github.io/doc/)
[![Selenide](https://img.shields.io/badge/Selenide-7.5.1-green.svg)](https://selenide.org/)
[![WebDriverManager](https://img.shields.io/badge/WebDriverManager-5.9.2-blue.svg)](https://github.com/bonigarcia/webdrivermanager)
[![Appium](https://img.shields.io/badge/Appium-9.3.0-orange.svg)](https://appium.io/)
[![Selenium](https://img.shields.io/badge/Selenium-4.27.0-green.svg)](https://www.selenium.dev/)
[![BrowserStack](https://img.shields.io/badge/BrowserStack-1.18.0-blue.svg)](https://www.browserstack.com/)
[![Allure](https://img.shields.io/badge/Allure-2.27.0-red.svg)](https://allure-framework.github.io/)
[![JavaFaker](https://img.shields.io/badge/JavaFaker-1.0.2-yellow.svg)](https://github.com/DiUS/java-faker)
[![AspectJ](https://img.shields.io/badge/AspectJ-1.9.22-purple.svg)](https://www.eclipse.org/aspectj/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.38-pink.svg)](https://projectlombok.org/)
[![Owner](https://img.shields.io/badge/Owner-1.0.12-lightgrey.svg)](https://owner.aeonbits.org/)

---

## 📋 Table of Contents

- [Project Description](#-project-description)
- [Architecture & Structure](#-architecture--structure)
- [Technology Stack](#-technology-stack)
- [Supported Platforms](#-supported-platforms)
- [Design Patterns](#-design-patterns)
- [Configuration & Secrets](#-configuration--secrets)
- [Quick Start](#-quick-start)
- [Reporting](#-reporting)
- [CI/CD Integration](#-cicd-integration)
- [Code Formatting](#-code-formatting)
- [Appium Setup](#-appium-setup)
- [Dependencies](#-dependencies)

---

## 🎯 Project Description

Comprehensive test automation framework for **API**, **Web**, and **Mobile** testing with:

- **API Testing** - REST Assured with PetStore API (Swagger)
- **Web Testing** - Selenide with SauceDemo application
- **Mobile Testing** - Appium with BrowserStack cloud and local execution (Android/iOS)
- **Reporting** - Allure Reports with GitHub Pages deployment
- **CI/CD** - GitHub Actions workflows with automatic test execution
- **Notifications** - Telegram integration for test results
- **Code Quality** - Spotless code formatting and quality checks

Built on clean architecture principles using modern design patterns (Singleton, Factory, Builder, Facade, Strategy, Iterator, Adapter, Decorator) for scalability, maintainability, and reliability.

---

## 🏗️ Architecture & Structure

### 📁 Project Structure

```
java_mobile/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/
│   │   │   ├── 📁 api/                  # API Testing
│   │   │   │   ├── 📁 models/           # Request/Response models
│   │   │   │   │   ├── BaseModel.java
│   │   │   │   │   ├── PetRequestModel.java
│   │   │   │   │   ├── PetResponseModel.java
│   │   │   │   │   ├── PetCategoryModel.java
│   │   │   │   │   ├── PetTagModel.java
│   │   │   │   │   └── 📁 comparison/   # Model comparison logic
│   │   │   │   │       ├── ModelComparator.java
│   │   │   │   │       ├── ModelAssertions.java
│   │   │   │   │       └── ModelComparisonConfigLoader.java
│   │   │   │   ├── 📁 requests/         # Request builders
│   │   │   │   │   ├── BaseRequest.java
│   │   │   │   │   ├── Endpoint.java
│   │   │   │   │   ├── RequestInterface.java
│   │   │   │   │   └── 📁 client/       # CRUD requesters
│   │   │   │   │       ├── CrudRequester.java
│   │   │   │   │       └── ValidatedCrudRequester.java
│   │   │   │   ├── 📁 specs/            # Request/Response specifications
│   │   │   │   │   ├── RequestSpecs.java
│   │   │   │   │   └── ResponseSpecs.java
│   │   │   │   └── 📁 steps/            # Step definitions
│   │   │   │       └── PetSteps.java
│   │   │   ├── 📁 mobile/               # Mobile Testing
│   │   │   │   ├── 📁 driver/           # Driver management
│   │   │   │   │   ├── AppDriver.java
│   │   │   │   │   ├── AppFactory.java
│   │   │   │   │   └── AppiumServerManager.java
│   │   │   │   ├── 📁 screens/          # Page Object Model
│   │   │   │   │   ├── BaseScreen.java
│   │   │   │   │   ├── LoginScreen.java
│   │   │   │   │   ├── ProductsScreen.java
│   │   │   │   │   ├── CartScreen.java
│   │   │   │   │   └── MenuScreen.java
│   │   │   │   └── 📁 listeners/        # Test listeners
│   │   │   │       └── BrowserStackListener.java
│   │   │   ├── 📁 web/                  # Web Testing
│   │   │   │   ├── 📁 pages/            # Page Object Model
│   │   │   │   │   ├── BasePage.java
│   │   │   │   │   ├── LoginPage.java
│   │   │   │   │   ├── ProductsPage.java
│   │   │   │   │   ├── CartPage.java
│   │   │   │   │   └── CheckoutPage.java
│   │   │   │   └── 📁 steps/            # Step definitions
│   │   │   │       └── UserSteps.java
│   │   │   └── 📁 utils/                # Utility classes
│   │   │       ├── ProjectConfig.java   # Configuration reader
│   │   │       ├── Constants.java       # Constants
│   │   │       ├── DataGenerator.java   # Factory Pattern for test data
│   │   │       └── RetryAnalyzer.java   # Retry mechanism
│   │   └── 📁 resources/
│   │       ├── 📁 api/
│   │       │   └── model-mapping.properties        # Model comparison config
│   │       └── 📁 apps/                            # APK/IPA files
│   │           ├── AppAndroid.apk
│   │           ├── AppiOSlocal.zip
│   │           └── AppiOSRemote.ipa
│   └── 📁 test/
│       ├── 📁 java/
│       │   ├── 📁 api/
│       │   │   ├── BaseApiTest.java                 # Base API test class
│       │   │   ├── CreatePetTest.java               # Create pet tests
│       │   │   ├── GetPetTest.java                  # Get pet tests
│       │   │   ├── UpdatePetTest.java               # Update pet tests
│       │   │   └── DeletePetTest.java               # Delete pet tests
│       │   ├── 📁 mobile/
│       │   │   ├── BaseMobileTest.java              # Base mobile test class
│       │   │   ├── LoginTest.java                   # Login tests
│       │   │   ├── NegativeLoginTest.java           # Negative login tests
│       │   │   ├── CartTest.java                    # Cart tests
│       │   │   └── SortTest.java                    # Sort tests
│       │   └── 📁 web/
│       │       ├── BaseWebTest.java                 # Base web test class
│       │       ├── LoginTest.java                   # Login tests
│       │       ├── ShoppingTest.java                # Shopping tests
│       │       └── CheckoutTest.java                # Checkout tests
│       └── 📁 resources/
│           ├── api-suite.xml                        # API TestNG suite
│           ├── mobile-suite.xml                     # Mobile TestNG suite
│           └── web-suite.xml                        # Web TestNG suite
├── config.properties                               # Configuration file
├── config.properties.example                      # Configuration template
├── GITHUB_SECRETS.example                         # GitHub Secrets template
├── pom.xml                                         # Maven configuration
├── README.md                                       # Project documentation
└── send-telegram-notification.sh                  # Telegram notification script
```

---

## 🛠️ Technology Stack

### 🎯 Core Technologies

| Category          | Technology        | Version | Purpose                      |
| ----------------- | ----------------- | ------- | ---------------------------- |
| **Language**      | Java              | 17      | Programming language         |
| **Build Tool**    | Maven             | 3.6+    | Dependency management        |
| **Testing**       | TestNG            | 7.10.2  | Test framework               |
| **API Testing**   | REST Assured      | 5.5.1   | API automation               |
| **Mobile Testing**| Appium            | 9.3.0   | Mobile app automation        |
| **Web Testing**   | Selenide          | 7.5.1   | Web UI automation            |
| **WebDriver**     | Selenium          | 4.27.0  | WebDriver implementation     |
| **Assertions**    | AssertJ           | 3.27.3  | Fluent assertions            |
| **Reporting**     | Allure            | 2.27.0  | Detailed test reporting      |
| **Cloud Testing** | BrowserStack SDK  | 1.18.0  | Cloud testing platform       |

### 🔧 Additional Tools

| Tool                  | Version | Purpose                             |
| --------------------- | ------- | ----------------------------------- |
| **JavaFaker**         | 1.0.2   | Test data generation                |
| **Jackson**           | 2.19.0  | JSON serialization/deserialization  |
| **Lombok**            | 1.18.38 | Boilerplate code reduction          |
| **Owner**             | 1.0.12  | Configuration management            |
| **WebDriverManager**  | 5.9.2   | WebDriver management                |
| **Spotless Maven**    | 2.43.0  | Code formatting and quality control |
| **AspectJ Weaver**    | 1.9.22  | Allure integration                  |
| **Maven Surefire**    | 3.5.2   | Test execution                      |

---

## 🌐 Supported Platforms

**Mobile:** Android 13.0+ / iOS 18.6+ (Local & BrowserStack Cloud)  
**API:** PetStore API (Swagger) - https://petstore.swagger.io/v2 (REST/JSON)  
**Web:** Chrome, Firefox, Edge, Safari (Windows, macOS, Linux) - SauceDemo - https://www.saucedemo.com

---

## 🎨 Design Patterns

### Creational Patterns

#### 1. Singleton

```java
// src/main/java/utils/ProjectConfig.java
@Config.Sources("file:config.properties")
public interface ProjectConfig extends Config {
    ProjectConfig CONFIG = ConfigFactory.create(ProjectConfig.class);
}
```

---

#### 2. Factory Method

```java
// src/main/java/mobile/driver/AppFactory.java
public class AppFactory {
    public static void launchApp() throws MalformedURLException {
        URI serverUrl = getServerUrl();
        
        if ("ios".equalsIgnoreCase(ProjectConfig.CONFIG.getPlatform())) {
            XCUITestOptions iosOptions = createIosOptions();
            driver = new IOSDriver(serverUrl.toURL(), iosOptions);
        } else {
            UiAutomator2Options androidOptions = createAndroidOptions();
            driver = new AndroidDriver(serverUrl.toURL(), androidOptions);
        }
        
        AppDriver.setDriver(driver);
    }
}
```

---

#### 3. Abstract Factory

```java
// src/main/java/mobile/driver/AppFactory.java
public class AppFactory {
    private static UiAutomator2Options createAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        boolean isCloud = Boolean.TRUE.equals(ProjectConfig.CONFIG.getIsCloud());
        
        if (isCloud) {
            options.setDeviceName(ProjectConfig.CONFIG.getAndroidCloudDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getAndroidCloudPlatformVersion())
                    .setAppPackage(ProjectConfig.CONFIG.getAndroidCloudAppPackage())
                    .setAppActivity(ProjectConfig.CONFIG.getAndroidCloudAppActivity());
        } else {
            options.setDeviceName(ProjectConfig.CONFIG.getAndroidLocalDeviceName())
                    .setPlatformVersion(ProjectConfig.CONFIG.getAndroidLocalPlatformVersion())
                    .setAppPackage(ProjectConfig.CONFIG.getAndroidLocalAppPackage())
                    .setAppActivity(ProjectConfig.CONFIG.getAndroidLocalAppActivity());
        }
        return options;
    }
    
    private static XCUITestOptions createIosOptions() {
        XCUITestOptions options = new XCUITestOptions();
        // Similar iOS options configuration
        return options;
    }
}
```

---

#### 4. Builder

```java
// src/main/java/api/models/PetRequestModel.java
@Data
@Builder
public class PetRequestModel extends BaseModel {
    private Long id;
    private String name;
    private String status;
    private PetCategoryModel category;
    private List<PetTagModel> tags;
}

// Usage:
PetRequestModel pet = PetRequestModel.builder()
        .id(1L)
        .name("Fluffy")
        .status("available")
        .category(category)
        .tags(tags)
        .build();
```

---

### Structural Patterns

#### 5. Adapter

```java
// src/main/java/api/requests/client/ValidatedCrudRequester.java
public class ValidatedCrudRequester<T extends BaseModel> 
        extends BaseRequest implements RequestInterface {
    private CrudRequester crudRequester;
    
    public ValidatedCrudRequester(RequestSpecification requestSpec, 
                                 Endpoint endpoint, 
                                 ResponseSpecification responseSpec) {
        super(requestSpec, endpoint, responseSpec);
        this.crudRequester = new CrudRequester(requestSpec, endpoint, responseSpec);
    }
    
    @Override
    public T post(BaseModel model) {
        return (T) crudRequester.post(model)
                .extract()
                .as(endpoint.getResponseModel());  // Adapts response to model
    }
}
```

---

#### 6. Decorator

```java
// src/main/java/web/pages/BasePage.java
public abstract class BasePage<T extends BasePage<T>> {
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
}
```

---

#### 7. Facade

```java
// src/main/java/api/specs/RequestSpecs.java
public class RequestSpecs {
    private RequestSpecs() {}
    
    public static RequestSpecification petStoreSpec() {
        return defaultRequestBuilder().build();
    }
    
    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilters(List.of(new RequestLoggingFilter(), 
                                   new ResponseLoggingFilter(), 
                                   new AllureRestAssured()))
                .setBaseUri(ProjectConfig.CONFIG.getApiBaseUrl());
    }
}
```

---

### Behavioral Patterns

#### 8. Strategy

```java
// src/main/java/mobile/driver/AppFactory.java
private static URI getServerUrl() {
    String url = Boolean.TRUE.equals(ProjectConfig.CONFIG.getIsCloud())
            ? ProjectConfig.CONFIG.getBrowserstackHubUrl()  // Cloud strategy
            : ProjectConfig.CONFIG.getAppiumLocalUrl();     // Local strategy
    return URI.create(url);
}
```

---

#### 9. Iterator

```java
// src/main/java/web/pages/BasePage.java
public abstract class BasePage<T extends BasePage<T>> {
    protected List<String> getAllTexts(ElementsCollection elements) {
        return elements.texts();  // Iterator pattern via Selenide
    }
}
```

---

## 🔐 Configuration & Secrets

### 📝 Configuration File

Create `config.properties` file in the project root based on [`config.properties.example`](config.properties.example).

### 🔑 GitHub Secrets

Configure secrets in GitHub (Settings → Secrets and variables → Actions) based on [`GITHUB_SECRETS.example`](GITHUB_SECRETS.example).

---

## 🚀 Quick Start

### 📋 Prerequisites

- **Java** >= 17
- **Maven** >= 3.6
- **Node.js** (for Appium)
- **Appium** >= 2.0
- **BrowserStack account** (for cloud testing)
- **Android Studio** (for local Android testing)
- **Xcode** (for local iOS testing)

### ⚡ Installation

```bash
# Clone repository
git clone <repository-url>
cd <project-directory>

# Copy configuration
cp config.properties.example config.properties

# Edit configuration
nano config.properties  # or any editor

# Install Appium (if not installed)
npm install -g appium

# Install dependencies (Maven will download automatically)
mvn clean install
```

### 🎯 First Run

```bash
# Run all tests (API / Web / Mobile)
mvn clean test -Papi
mvn clean test -Pweb
mvn clean test -Pmobile

# Run specific test
mvn clean test -Papi -Dtest=CreatePetTest
mvn clean test -Pweb -Dtest=LoginTest
mvn clean test -Pmobile -Dtest=LoginTest

# Run with custom configuration (override config.properties)
mvn clean test -Papi -Dapi.baseUrl=https://petstore.swagger.io/v2
mvn clean test -Pweb -Dweb.headless=true -Dweb.credentials.username=standard_user
mvn clean test -Pmobile -Dmobile.platform=android -Dmobile.isCloud=true
```

---

### 🔄 Parallel Execution

Configure parallel execution in TestNG suite files (`api-suite.xml`, `web-suite.xml`, `mobile-suite.xml`):

```xml
<!-- API Tests -->
<suite name="API Test Suite" parallel="classes" thread-count="4">
    <test name="API Tests">
        <classes>
            <class name="api.CreatePetTest"/>
            <class name="api.GetPetTest"/>
            <class name="api.UpdatePetTest"/>
            <class name="api.DeletePetTest"/>
        </classes>
    </test>
</suite>

<!-- Web Tests -->
<suite name="Web Test Suite" parallel="classes" thread-count="3">
    <test name="Web Tests">
        <classes>
            <class name="web.LoginTest"/>
            <class name="web.ShoppingTest"/>
            <class name="web.CheckoutTest"/>
        </classes>
    </test>
</suite>

<!-- Mobile Tests -->
<suite name="Mobile Test Suite" parallel="methods" thread-count="5">
    <test name="Mobile Tests">
        <classes>
            <class name="mobile.LoginTest"/>
            <class name="mobile.NegativeLoginTest"/>
            <class name="mobile.CartTest"/>
            <class name="mobile.SortTest"/>
        </classes>
    </test>
</suite>
```

> **⚠️ Important for Local Testing:**  
> When running mobile tests **locally**, use `thread-count="1"`:
> ```xml
> <suite name="Mobile Test Suite" parallel="methods" thread-count="1">
> ```
> Local Appium server typically supports only one session at a time. Parallel execution works only on BrowserStack (cloud).

---

### 🔄 Retry Mechanism

Framework includes retry logic for failed tests. The `RetryAnalyzer` class automatically retries failed tests up to 3 times before marking them as failed.

**Implementation:** [`src/main/java/utils/RetryAnalyzer.java`](src/main/java/utils/RetryAnalyzer.java)

The retry analyzer implements TestNG's `IRetryAnalyzer` interface and can be applied to test methods using the `@Test(retryAnalyzer = RetryAnalyzer.class)` annotation.

---

## 📊 Reporting

### 🎨 Allure Reports

```bash
# Generate report
mvn allure:report

# Serve report locally
mvn allure:serve
```

### 🔔 Telegram Notifications

Automatic notifications about test results via `send-telegram-notification.sh`:

- 🟢 **Success** - all tests passed (0 failed)
- 🔴 **Failed** - some tests failed (> 0 failed)
- 🟡 **No Tests** - no tests executed

```bash
# Send notification manually
./send-telegram-notification.sh
```

---

## 🔄 CI/CD Integration

### 🚀 GitHub Actions

Four workflows available:

1. **All Tests** (`all-tests.yml`) - Manual only, runs all tests → Combined report
2. **API Tests** (`api-tests.yml`) - Auto on API changes / Manual (4 parallel classes)
3. **Web Tests** (`web-tests.yml`) - Auto on Web changes / Manual (3 parallel classes)
4. **Mobile Tests** (`mobile-tests.yml`) - Auto on Mobile changes / Manual (5 parallel methods, BrowserStack)

**All workflows:** Lint Check → Test Execution → Allure Report → GitHub Pages → Telegram Notification

---

## 📝 Code Formatting

The project uses **Spotless Maven Plugin** for code formatting:

```bash
# Format code
mvn spotless:apply

# Check code formatting
mvn spotless:check
```

---

## 🔧 Appium Setup

### Local Appium Server

```bash
# Start Appium server
appium

# Stop all Node.js processes (if Appium server hangs)
killall node
```

### Appium Inspector Configuration

#### Android Local
```json
{
  "platformName": "Android",
  "appium:deviceName": "Pixel 4",
  "appium:platformVersion": "14",
  "appium:automationName": "UiAutomator2"
}
```

#### iOS Local
```json
{
  "platformName": "iOS",
  "appium:deviceName": "iPhone 16 Plus",
  "appium:platformVersion": "18.6",
  "appium:automationName": "XCUITest",
  "appium:udid": "YOUR_DEVICE_UDID"
}
```

> **Note:** For iOS Local, you need to specify the `appium:udid` of your physical device or simulator. You can find the UDID using:
> - **Simulator:** `xcrun simctl list devices`
> - **Physical device:** Connect device and check in Xcode → Window → Devices and Simulators

---

## 📦 Dependencies

All dependencies are managed via Maven in `pom.xml`:

**Stack:**
- **Java** - Programming language
- **Maven** - Build tool and dependency management
- **TestNG** - Test framework

**API:**
- **REST Assured** - API testing framework
- **Jackson** - JSON serialization/deserialization
- **AssertJ** - Fluent assertions
- **Allure REST Assured** - API request/response logging

**Web:**
- **Selenide** - Web UI automation framework
- **WebDriverManager** - Automatic WebDriver management
- **Allure Selenide** - Web test reporting

**Mobile:**
- **Appium** - Appium integration
- **Selenium** - WebDriver implementation
- **BrowserStack SDK** - Cloud testing

**Common:**
- **JavaFaker** - Test data generation
- **Allure TestNG** - Reporting
- **AspectJ Weaver** - Allure integration
- **Lombok** - Boilerplate code reduction
- **Owner** - Configuration management

---

<div align="center">

**Made with ❤️ by Polishevskyi**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Polishevskyi)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/polishevskyi/)

</div>
