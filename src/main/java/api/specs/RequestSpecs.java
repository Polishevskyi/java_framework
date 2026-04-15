package api.specs;

import api.requests.AuthService;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ProjectConfig;

public class RequestSpecs {
    private RequestSpecs() {}

    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .addFilter(new AllureRestAssured())
                .setBaseUri(ProjectConfig.CONFIG.getApiBaseUrl());
    }

    public static RequestSpecification bookingSpec() {
        return defaultRequestBuilder().build();
    }

    public static RequestSpecification authenticatedBookingSpec() {
        return defaultRequestBuilder()
                .addCookie("token", AuthService.getToken())
                .build();
    }
}
