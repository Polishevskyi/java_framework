package api.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponseModel extends BaseModel {
    private Long id;
    private PetCategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<PetTagModel> tags;
    private String status;
}
