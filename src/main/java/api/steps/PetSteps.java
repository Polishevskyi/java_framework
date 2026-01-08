package api.steps;

import api.models.PetRequestModel;
import api.models.PetResponseModel;
import api.requests.Endpoint;
import api.requests.client.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import io.qameta.allure.Allure;
import lombok.Data;
import org.apache.http.HttpStatus;
import utils.DataGenerator;

public class PetSteps {
    private PetSteps() {}

    public static PetResponse createPet() {
        return createPet(null);
    }

    public static PetResponse createPet(PetRequestModel petData) {
        PetRequestModel requestData = petData != null ? petData : DataGenerator.generatePet();
        return Allure.step("Create pet with name: " + requestData.getName(), () -> {
            PetResponseModel responseData = createRequester(Endpoint.CREATE_PET).post(requestData);
            return new PetResponse(requestData, responseData, HttpStatus.SC_OK);
        });
    }

    public static PetResponse getPetById(Long petId) {
        return Allure.step("Get pet by ID: " + petId, () -> {
            PetResponseModel responseData =
                    (PetResponseModel) createRequester(Endpoint.GET_PET).get(petId);
            return new PetResponse(null, responseData, HttpStatus.SC_OK);
        });
    }

    public static PetResponse updatePet(PetRequestModel petData) {
        return Allure.step("Update pet with ID: " + petData.getId(), () -> {
            PetResponseModel responseData =
                    (PetResponseModel) createRequester(Endpoint.UPDATE_PET).put(petData);
            return new PetResponse(petData, responseData, HttpStatus.SC_OK);
        });
    }

    public static PetResponse deletePet(Long petId) {
        return Allure.step("Delete pet by ID: " + petId, () -> {
            createRequester(Endpoint.DELETE_PET).delete(petId);
            return new PetResponse(null, null, HttpStatus.SC_OK);
        });
    }

    private static ValidatedCrudRequester<PetResponseModel> createRequester(Endpoint endpoint) {
        return new ValidatedCrudRequester<>(
                RequestSpecs.petStoreSpec(), endpoint, ResponseSpecs.requestReturnsOKSpec());
    }

    @Data
    public static class PetResponse {
        private final PetRequestModel requestData;
        private final PetResponseModel responseData;
        private final Integer status;
    }
}
