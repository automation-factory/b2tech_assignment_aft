package api.wrapper;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import static org.apache.http.HttpStatus.SC_OK;

@AllArgsConstructor
@SuppressWarnings("unchecked")
public abstract class ApiResponseWrapper<T, R> {

    @NonNull
    private final Response response;

    protected abstract R body();

    public T verifyOkStatusCode() {
        return verifyStatusCode(SC_OK);
    }

    public T verifyStatusCode(int statusCode) {
        response
                .then()
                .assertThat()
                .statusCode(statusCode);
        return (T) this;
    }

    public <V> V body(Class<V> cls) {
        return response.as(cls);
    }

}
