package api.common;

import api.util.JsonUtil;
import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
public class ApiPojoRequest<T> implements IRequestData {
    @NonNull
    private T body;
    @Builder.Default
    private List<Header> headers = Collections.emptyList();

    public ApiPojoRequest(@NonNull T body) {
        this.body = body;
    }

    public void setHeader(Header header) {
        headers = List.of(header);
    }

    @Override
    public String getBodyAsString() {
        return JsonUtil.writeValueAsString(body);
    }

}
