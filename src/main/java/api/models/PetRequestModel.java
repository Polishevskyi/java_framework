package api.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRequestModel extends BaseModel {
    private Long id;
    private PetCategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<PetTagModel> tags;
    private String status;
}
