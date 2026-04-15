package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.steps.BookingSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseApiTest {

    @Test(description = "Verify that booking can be deleted successfully by ID")
    public void verifyBookingCanBeDeletedSuccessfully() {
        BookingSteps.BookingResponse createdBooking = BookingSteps.createBooking();
        Long bookingId = createdBooking.getBookingId();

        BookingSteps.BookingResponse deletedBooking = BookingSteps.deleteBooking(bookingId);

        assertThat(deletedBooking.getStatus()).isEqualTo(HttpStatus.SC_CREATED);
    }
}
