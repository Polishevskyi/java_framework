package api.steps;

import api.models.BookingCreateResponseModel;
import api.models.BookingRequestModel;
import api.requests.Endpoint;
import api.requests.client.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import io.qameta.allure.Allure;
import lombok.Data;
import org.apache.http.HttpStatus;
import utils.DataGenerator;

public class BookingSteps {
    private BookingSteps() {}

    public static BookingResponse createBooking() {
        return createBooking(null);
    }

    public static BookingResponse createBooking(BookingRequestModel bookingData) {
        BookingRequestModel requestData = bookingData != null ? bookingData : DataGenerator.generateBooking();
        return Allure.step("Create booking for: " + requestData.getFirstname() + " " + requestData.getLastname(), () -> {
            BookingCreateResponseModel responseData =
                    (BookingCreateResponseModel) createRequester(Endpoint.CREATE_BOOKING).post(requestData);
            return new BookingResponse(requestData, responseData.getBooking(), responseData.getBookingid(), HttpStatus.SC_OK);
        });
    }

    public static BookingResponse getBookingById(Long bookingId) {
        return Allure.step("Get booking by ID: " + bookingId, () -> {
            BookingRequestModel responseData =
                    (BookingRequestModel) createRequester(Endpoint.GET_BOOKING).get(bookingId);
            return new BookingResponse(null, responseData, bookingId, HttpStatus.SC_OK);
        });
    }

    public static BookingResponse updateBooking(Long bookingId, BookingRequestModel bookingData) {
        return Allure.step("Update booking with ID: " + bookingId, () -> {
            BookingRequestModel responseData =
                    (BookingRequestModel) createAuthRequester(Endpoint.UPDATE_BOOKING).put(bookingId, bookingData);
            return new BookingResponse(bookingData, responseData, bookingId, HttpStatus.SC_OK);
        });
    }

    public static BookingResponse deleteBooking(Long bookingId) {
        return Allure.step("Delete booking by ID: " + bookingId, () -> {
            createAuthDeleteRequester(Endpoint.DELETE_BOOKING).delete(bookingId);
            return new BookingResponse(null, null, bookingId, HttpStatus.SC_CREATED);
        });
    }

    private static ValidatedCrudRequester<BookingCreateResponseModel> createRequester(Endpoint endpoint) {
        return new ValidatedCrudRequester<>(
                RequestSpecs.bookingSpec(), endpoint, ResponseSpecs.requestReturnsOKSpec());
    }

    private static ValidatedCrudRequester<BookingRequestModel> createAuthRequester(Endpoint endpoint) {
        return new ValidatedCrudRequester<>(
                RequestSpecs.authenticatedBookingSpec(), endpoint, ResponseSpecs.requestReturnsOKSpec());
    }

    private static ValidatedCrudRequester<BookingRequestModel> createAuthDeleteRequester(Endpoint endpoint) {
        return new ValidatedCrudRequester<>(
                RequestSpecs.authenticatedBookingSpec(), endpoint, ResponseSpecs.requestReturnsCreatedSpec());
    }

    @Data
    public static class BookingResponse {
        private final BookingRequestModel requestData;
        private final BookingRequestModel responseData;
        private final Long bookingId;
        private final Integer status;
    }
}
