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

public class CrudRequester extends BaseRequest implements RequestInterface {
    public CrudRequester(
            RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    @Step("POST request to {this.endpoint}")
    public ValidatableResponse post(BaseModel model) {
        var body = model == null ? "" : model;
        return given().spec(requestSpecification)
                .body(body)
                .post(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    @Step("GET request to {endpoint} with id {id}")
    public ValidatableResponse get(long id) {
        String url =
                endpoint.getUrl().replace("{accountId}", String.valueOf(id)).replace("{petId}", String.valueOf(id));
        return given().spec(requestSpecification).get(url).then().assertThat().spec(responseSpecification);
    }

    @Step("GET request to {endpoint}")
    public ValidatableResponse get() {
        return given().spec(requestSpecification)
                .get(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    @Step("PUT request to {endpoint} with body {model}")
    public ValidatableResponse put(BaseModel model) {
        return given().spec(requestSpecification)
                .body(model)
                .put(endpoint.getUrl())
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
                .delete(url)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
