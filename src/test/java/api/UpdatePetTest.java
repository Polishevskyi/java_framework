package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.models.PetRequestModel;
import api.models.comparison.ModelAssertions;
import api.steps.PetSteps;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.DataGenerator;

public class UpdatePetTest extends BaseApiTest {

    @Test
    @Description("Verify that pet can be updated successfully with new data")
    public void verifyPetCanBeUpdatedSuccessfully() {
        PetSteps.PetResponse createdPet = PetSteps.createPet();

        PetRequestModel updatedPetData = DataGenerator.generatePetUpdateData(createdPet.getResponseData());
        PetSteps.PetResponse updatedPet = PetSteps.updatePet(updatedPetData);

        assertThat(updatedPet.getStatus()).isEqualTo(HttpStatus.SC_OK);

        softly.assertThat(updatedPet.getResponseData()).isNotNull();
        softly.assertThat(updatedPet.getResponseData().getId())
                .isEqualTo(createdPet.getResponseData().getId());
        softly.assertThat(updatedPet.getResponseData().getName()).isEqualTo(updatedPetData.getName());

        ModelAssertions.assertThatModels(updatedPetData, updatedPet.getResponseData())
                .match();
    }
}
