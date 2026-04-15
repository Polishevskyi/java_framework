package api.requests;

import api.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoint {
    CREATE_BOOKING("/booking", BookingRequestModel.class, BookingCreateResponseModel.class),

    GET_BOOKING("/booking/{bookingId}", BaseModel.class, BookingRequestModel.class),

    UPDATE_BOOKING("/booking/{bookingId}", BookingRequestModel.class, BookingRequestModel.class),

    DELETE_BOOKING("/booking/{bookingId}", BaseModel.class, BaseModel.class);

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}
