package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.comparison.ModelAssertions;
import api.steps.PetSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class GetPetTest extends BaseApiTest {

    @Test(description = "Verify that pet can be retrieved successfully by ID")
    public void verifyPetCanBeRetrievedById() {
        PetSteps.PetResponse createdPet = PetSteps.createPet();
        PetSteps.PetResponse retrievedPet =
                PetSteps.getPetById(createdPet.getResponseData().getId());

        assertThat(retrievedPet.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(retrievedPet.getResponseData()).isNotNull();

        ModelAssertions.assertThatModels(createdPet.getResponseData(), retrievedPet.getResponseData())
                .match();
    }
}
