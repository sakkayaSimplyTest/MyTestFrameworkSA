package framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11GetHeaderTest {

    public static final String BASE_URI = "https://api.github.com/";


    @Test
    void getReturns200() throws IOException, InterruptedException {

        // Arrange

        HttpClient httpClient = HttpClient.newBuilder().build();  // ALTERNATIVELY --> HttpClient.newHttpClient();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URI))
//                .GET()   // Default olarak var silebilirsin de ekleyebilirsin de
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act
        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());

        int actualCode = response.statusCode();

        // Assert
        Assertions.assertEquals(200, actualCode);

    }

    @Test
    void contentTypeIsJson() throws IOException, InterruptedException {

        // Arrange

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URI))
                .GET()
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act
        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
        String contentType = response.headers().firstValue("content-type").get();


        // Assert
        Assertions.assertEquals("application/json; charset=utf-8", contentType);
    }

    @Test
    void xRateLimitIsPresent() throws IOException, InterruptedException {

        //Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URI))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();
        
        // Act     
        HttpResponse<Void> response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());

        String xRateLimit = response.headers().firstValue("X-Ratelimit-Limit").get();

        //Assert
        Assertions.assertEquals(60, Integer.parseInt(xRateLimit));
    }




}
