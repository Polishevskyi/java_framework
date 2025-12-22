package api.requests.client;

import static io.restassured.RestAssured.given;

import api.models.BaseModel;
import api.requests.BaseRequest;
import api.requests.Endpoint;
import api.requests.RequestInterface;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigReader;
import utils.listeners.AllureListener;

public class CrudRequester extends BaseRequest implements RequestInterface {
    private static final String API_VERSION = ConfigReader.getProperty("api.version");

    public CrudRequester(
            RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        return AllureListener.log("POST request to " + endpoint.getUrl(), () -> {
            var body = model == null ? "" : model;
            return given().spec(requestSpecification)
                    .body(body)
                    .post(API_VERSION + endpoint.getUrl())
                    .then()
                    .assertThat()
                    .spec(responseSpecification);
        });
    }

    @Override
    @Step("GET request to {endpoint} with id {id}")
    public ValidatableResponse get(long id) {
        String url =
                endpoint.getUrl().replace("{accountId}", String.valueOf(id)).replace("{petId}", String.valueOf(id));
        return given().spec(requestSpecification)
                .get(API_VERSION + url)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Step("GET request to {endpoint}")
    public ValidatableResponse get() {
        return given().spec(requestSpecification)
                .get(API_VERSION + endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    @Step("PUT request to {endpoint} with body {model}")
    public ValidatableResponse put(BaseModel model) {
        return given().spec(requestSpecification)
                .body(model)
                .put(API_VERSION + endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    @Step("DELETE request for {endpoint} with id {id}")
    public ValidatableResponse delete(long id) {
        String url =
                endpoint.getUrl().replace("{accountId}", String.valueOf(id)).replace("{petId}", String.valueOf(id));
        return given().spec(requestSpecification)
                .delete(API_VERSION + url)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
