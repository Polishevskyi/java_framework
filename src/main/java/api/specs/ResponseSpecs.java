package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class ResponseSpecs {
    private ResponseSpecs() {}

    private static ResponseSpecBuilder defaultResponseBuilder() {
        return new ResponseSpecBuilder();
    }

    public static ResponseSpecification requestReturnsOKSpec() {
        return defaultResponseBuilder().expectStatusCode(HttpStatus.SC_OK).build();
    }

    public static ResponseSpecification requestReturnsBadRequestSpec() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification requestReturnsBadRequestSpec(String errorMessage) {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody("message", Matchers.equalTo(errorMessage))
                .build();
    }

    public static ResponseSpecification requestReturnsNotFoundSpec() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }
}
