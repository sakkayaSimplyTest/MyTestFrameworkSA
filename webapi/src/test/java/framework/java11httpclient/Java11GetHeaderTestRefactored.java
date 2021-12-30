package framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newBuilder;

public class Java11GetHeaderTestRefactored {

    public static final String BASE_URI = "https://api.github.com/";

    static HttpClient httpClient = newBuilder().build();
    static HttpResponse<Void> response;


    @BeforeAll
    static void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        // Arrange
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URI))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act - send request
        response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
    }

    @ParameterizedTest
    @CsvSource({
            "X-Ratelimit-Limit, 60",                             //Input, Expected Output
            "content-type, application/json; charset=utf-8",      //Input, Expected Output
            "server, GitHub.com",
            "x-frame-options, deny"
    })
    void parametrizedTestForHeaders(String header, String expectedValue)  {

        String contentType = response.headers().firstValue(header).get();

        // Assert
        Assertions.assertEquals(expectedValue, contentType);
    }

    @Test
    void getReturns200(){
        // Act
        int actualCode = response.statusCode();

        // Assert
        Assertions.assertEquals(200, actualCode);

    }



}
