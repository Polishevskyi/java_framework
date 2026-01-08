package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.comparison.ModelAssertions;
import api.steps.PetSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreatePetTest extends BaseApiTest {

    @Test(description = "Verify that pet can be created successfully with valid data")
    public void verifyPetCanBeCreatedSuccessfully() {
        PetSteps.PetResponse response = PetSteps.createPet();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(response.getResponseData()).isNotNull();
        softly.assertThat(response.getResponseData().getId()).isNotNull();

        ModelAssertions.assertThatModels(response.getRequestData(), response.getResponseData())
                .match();
    }
}
