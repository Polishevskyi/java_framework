package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.BookingRequestModel;
import api.models.comparison.ModelAssertions;
import api.steps.BookingSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.DataGenerator;

public class UpdateBookingTest extends BaseApiTest {

    @Test(description = "Verify that booking can be updated successfully with new data")
    public void verifyBookingCanBeUpdatedSuccessfully() {
        BookingSteps.BookingResponse createdBooking = BookingSteps.createBooking();

        BookingRequestModel updatedBookingData = DataGenerator.generateBookingUpdate(createdBooking.getResponseData());
        BookingSteps.BookingResponse updatedBooking =
                BookingSteps.updateBooking(createdBooking.getBookingId(), updatedBookingData);

        assertThat(updatedBooking.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(updatedBooking.getResponseData()).isNotNull();
        softly.assertThat(updatedBooking.getResponseData().getFirstname()).isEqualTo(updatedBookingData.getFirstname());
        softly.assertThat(updatedBooking.getResponseData().getLastname()).isEqualTo(updatedBookingData.getLastname());
        softly.assertThat(updatedBooking.getResponseData().getTotalprice())
                .isEqualTo(updatedBookingData.getTotalprice());

        ModelAssertions.assertThatModels(updatedBookingData, updatedBooking.getResponseData())
                .match();
    }
}
