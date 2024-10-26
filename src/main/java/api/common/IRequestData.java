package api.common;

import io.restassured.http.Header;

import java.util.List;

public interface IRequestData {
    String getBodyAsString();

    List<Header> getHeaders();
}
