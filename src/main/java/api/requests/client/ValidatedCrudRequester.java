package api.requests.client;

import api.models.BaseModel;
import api.requests.BaseRequest;
import api.requests.Endpoint;
import api.requests.RequestInterface;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ValidatedCrudRequester<T extends BaseModel> extends BaseRequest implements RequestInterface {
    private CrudRequester crudRequester;

    public ValidatedCrudRequester(
            RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
        this.crudRequester = new CrudRequester(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T post(BaseModel model) {
        return (T) crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object get(long id) {
        return (T) crudRequester.get(id).extract().as(endpoint.getResponseModel());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object put(BaseModel model) {
        return (T) crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object delete(long id) {
        return (T) crudRequester.get(id).extract().as(endpoint.getResponseModel());
    }
}
