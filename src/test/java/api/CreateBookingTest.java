package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.comparison.ModelAssertions;
import api.steps.BookingSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseApiTest {

    @Test(description = "Verify that booking can be created successfully with valid data")
    public void verifyBookingCanBeCreatedSuccessfully() {
        BookingSteps.BookingResponse response = BookingSteps.createBooking();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(response.getResponseData()).isNotNull();
        softly.assertThat(response.getBookingId()).isNotNull();

        ModelAssertions.assertThatModels(response.getRequestData(), response.getResponseData())
                .match();
    }
}
