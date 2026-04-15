package utils;

import api.models.BookingDatesModel;
import api.models.BookingRequestModel;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataGenerator {

    private static final Faker FAKER = new Faker();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DataGenerator() {}

    public static BookingRequestModel generateBooking() {
        LocalDate checkin = LocalDate.now().plusDays(FAKER.number().numberBetween(1, 30));
        LocalDate checkout = checkin.plusDays(FAKER.number().numberBetween(1, 14));

        return BookingRequestModel.builder()
                .firstname(FAKER.name().firstName())
                .lastname(FAKER.name().lastName())
                .totalprice(FAKER.number().numberBetween(50, 500))
                .depositpaid(FAKER.bool().bool())
                .bookingdates(BookingDatesModel.builder()
                        .checkin(checkin.format(DATE_FORMAT))
                        .checkout(checkout.format(DATE_FORMAT))
                        .build())
                .additionalneeds(FAKER.options().option("Breakfast", "Lunch", "Dinner", "Airport Transfer"))
                .build();
    }

    public static BookingRequestModel generateBookingUpdate(BookingRequestModel existing) {
        return existing.toBuilder()
                .firstname(FAKER.name().firstName())
                .lastname(FAKER.name().lastName())
                .totalprice(FAKER.number().numberBetween(50, 500))
                .build();
    }
}
