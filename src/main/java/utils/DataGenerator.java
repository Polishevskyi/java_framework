package utils;

import api.models.PetCategoryModel;
import api.models.PetRequestModel;
import api.models.PetResponseModel;
import api.models.PetTagModel;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class DataGenerator {

    private static final Faker FAKER = new Faker();
    private static final List<String> PET_STATUSES = List.of("available", "pending", "sold");
    private static final long MAX_PET_ID = 999_999L;
    private static final long MAX_CATEGORY_TAG_ID = 100L;

    private DataGenerator() {}

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String password() {
        return FAKER.internet().password(18, 20, true, true, true);
    }

    public static PetRequestModel generatePet() {
        return PetRequestModel.builder()
                .id(generatePetId())
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

    private static Long generatePetId() {
        return ThreadLocalRandom.current().nextLong(1, MAX_PET_ID);
    }

    private static PetCategoryModel generateCategory() {
        return PetCategoryModel.builder()
                .id(ThreadLocalRandom.current().nextLong(1, MAX_CATEGORY_TAG_ID))
                .name(FAKER.animal().name())
                .build();
    }

    private static PetTagModel generateTag() {
        return PetTagModel.builder()
                .id(ThreadLocalRandom.current().nextLong(1, MAX_CATEGORY_TAG_ID))
                .name(FAKER.animal().name())
                .build();
    }

    private static String generateRandomPetStatus() {
        return PET_STATUSES.get(ThreadLocalRandom.current().nextInt(PET_STATUSES.size()));
    }
}
