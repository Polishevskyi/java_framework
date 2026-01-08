package api;

import static org.assertj.core.api.Assertions.assertThat;

import api.steps.PetSteps;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class DeletePetTest extends BaseApiTest {

    @Test(description = "Verify that pet can be deleted successfully by ID")
    public void verifyPetCanBeDeletedSuccessfully() {
        PetSteps.PetResponse createdPet = PetSteps.createPet();
        Long petId = createdPet.getResponseData().getId();

        PetSteps.PetResponse deletedPet = PetSteps.deletePet(petId);

        assertThat(deletedPet.getStatus()).isEqualTo(HttpStatus.SC_OK);
    }
}
