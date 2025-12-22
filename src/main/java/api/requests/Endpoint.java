package api.requests;

import api.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoint {
    CREATE_PET("/pet", PetRequestModel.class, PetResponseModel.class),

    GET_PET("/pet/{petId}", BaseModel.class, PetResponseModel.class),

    UPDATE_PET("/pet", PetRequestModel.class, PetResponseModel.class),

    DELETE_PET("/pet/{petId}", BaseModel.class, PetResponseModel.class);

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}
