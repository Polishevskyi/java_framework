package utils;

import api.models.PetCategoryModel;
import api.models.PetRequestModel;
import api.models.PetResponseModel;
import api.models.PetTagModel;
import com.github.javafaker.Faker;
import java.util.*;

public final class DataGenerator {

    private static final Faker FAKER = new Faker();

    private DataGenerator() {}

    public static PetRequestModel generatePet() {
        return PetRequestModel.builder()
                .id(FAKER.number().numberBetween(1L, 999_999L))
                .name(FAKER.animal().name())
                .category(generateCategory())
                .photoUrls(List.of(FAKER.internet().image()))
                .tags(List.of(generateTag()))
                .status(generateRandomPetStatus())
                .build();
    }

    public static PetRequestModel generatePetUpdateData(PetResponseModel existingPet) {
        return PetRequestModel.builder()
                .id(existingPet.getId())
                .name(FAKER.animal().name())
                .category(existingPet.getCategory())
                .photoUrls(existingPet.getPhotoUrls())
                .tags(existingPet.getTags())
                .status(generateRandomPetStatus())
                .build();
    }

    private static PetCategoryModel generateCategory() {
        return PetCategoryModel.builder()
                .id(FAKER.number().numberBetween(1L, 100L))
                .name(FAKER.animal().name())
                .build();
    }

    private static PetTagModel generateTag() {
        return PetTagModel.builder()
                .id(FAKER.number().numberBetween(1L, 100L))
                .name(FAKER.animal().name())
                .build();
    }

    private static String generateRandomPetStatus() {
        List<String> statuses = List.of("available", "pending", "sold");
        return statuses.get(FAKER.number().numberBetween(0, statuses.size()));
    }
}
