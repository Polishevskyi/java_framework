package api.requests;

import api.models.BaseModel;

public interface RequestInterface {
    Object post(BaseModel model);

    Object get(long id);

    Object put(BaseModel model);

    Object delete(long id);
}
