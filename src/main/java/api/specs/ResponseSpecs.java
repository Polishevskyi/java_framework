package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class ResponseSpecs {
    private ResponseSpecs() {}

    public static ResponseSpecification requestReturnsOKSpec() {
        return new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK).build();
    }

    public static ResponseSpecification requestReturnsCreatedSpec() {
        return new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED).build();
    }
}
