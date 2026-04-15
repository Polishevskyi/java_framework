package api.requests;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import java.util.Map;
import utils.ProjectConfig;

public class AuthService {
    private static volatile String token;

    private AuthService() {}

    public static String getToken() {
        if (token == null) {
            synchronized (AuthService.class) {
                if (token == null) {
                    token = given()
                            .contentType(ContentType.JSON)
                            .accept("application/json")
                            .baseUri(ProjectConfig.CONFIG.getApiBaseUrl())
                            .body(Map.of(
                                    "username", ProjectConfig.CONFIG.getApiCredentialsUsername(),
                                    "password", ProjectConfig.CONFIG.getApiCredentialsPassword()))
                            .post("/auth")
                            .then()
                            .statusCode(200)
                            .extract()
                            .path("token");
                }
            }
        }
        return token;
    }
}
