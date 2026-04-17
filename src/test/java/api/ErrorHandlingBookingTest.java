package api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import api.requests.Endpoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorHandlingBookingTest extends BaseMockApiTest {

    @DataProvider(name = "errorStatuses")
    public Object[] errorStatuses() {
        return new Object[] {
            HttpStatus.SC_BAD_REQUEST,
            HttpStatus.SC_UNAUTHORIZED,
            HttpStatus.SC_FORBIDDEN,
            HttpStatus.SC_NOT_FOUND,
            HttpStatus.SC_UNPROCESSABLE_ENTITY,
            HttpStatus.SC_INTERNAL_SERVER_ERROR,
            HttpStatus.SC_BAD_GATEWAY,
            HttpStatus.SC_SERVICE_UNAVAILABLE,
            HttpStatus.SC_GATEWAY_TIMEOUT,
        };
    }

    @Test(dataProvider = "errorStatuses")
    public void verifyBookingErrorResponseIsHandledCorrectly(int statusCode) {
        String reasonPhrase = EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, null);
        String url = Endpoint.GET_BOOKING.getUrl().replace("{id}", "1");

        wireMockServer.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\":\"" + reasonPhrase + "\"}")));

        Response response = RestAssured.given()
                .baseUri("http://localhost:" + wireMockServer.port())
                .when()
                .get(url);

        softly.assertThat(response.statusCode()).isEqualTo(statusCode);
        softly.assertThat(response.jsonPath().getString("error")).isEqualTo(reasonPhrase);
    }
}
