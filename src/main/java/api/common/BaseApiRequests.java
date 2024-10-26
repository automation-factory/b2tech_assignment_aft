package api.common;

import config.Config;
import config.EnvironmentConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@UtilityClass
public class BaseApiRequests {

    public static final EnvironmentConfig ENVIRONMENT_CONFIG = Config.environmentConfig();
    private static final List<Filter> LOGGING_FILTERS = List.of(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());

    public RequestSpecification getCommonRequestSpecification() {
        var config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", ENVIRONMENT_CONFIG.timeout())
                .setParam("http.connection.timeout", ENVIRONMENT_CONFIG.timeout()));

        var specification = new RequestSpecBuilder()
                .setConfig(config)
                .setBaseUri(ENVIRONMENT_CONFIG.baseUrl())
                .setBasePath("/")
                .addHeader("Content-Type", "application/json")
                .addFilters(LOGGING_FILTERS)
                .build();
        return given().spec(specification);
    }

    public static Response postRequest(String endpoint, @NonNull IRequestData requestData, String schemaPath) {
        var requestSpecification = getCommonRequestSpecification();

        for (Header header : requestData.getHeaders()) {
            requestSpecification.header(header.getName(), header.getValue());
        }

        var response = requestSpecification
                .body(requestData.getBodyAsString())
                .post(endpoint);

        if (schemaPath != null && !schemaPath.isEmpty()) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
        }

        return response;
    }

}
