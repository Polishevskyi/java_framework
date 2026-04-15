package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.comparison.ModelAssertions;
import api.steps.BookingSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseApiTest {

    @Test(description = "Verify that booking can be retrieved successfully by ID")
    public void verifyBookingCanBeRetrievedById() {
        BookingSteps.BookingResponse createdBooking = BookingSteps.createBooking();
        BookingSteps.BookingResponse retrievedBooking =
                BookingSteps.getBookingById(createdBooking.getBookingId());

        assertThat(retrievedBooking.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(retrievedBooking.getResponseData()).isNotNull();

        ModelAssertions.assertThatModels(createdBooking.getResponseData(), retrievedBooking.getResponseData())
                .match();
    }
}
