package framework.restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class BasicRestAssuredApiTest {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void getStatusCodeIs200() {
        RestAssured.get("https://api.github.com/")
                .then()
                .statusCode(200);
    }

    @Test
    public void headersContainCorrectValues() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .header("content-type", "application/json; charset=utf-8")
                .header("X-Ratelimit-Limit", "60");
    }

    @Test
    public void bodyContainsCorrectValues() {

        RestAssured.get("https://api.github.com/users/andrejs-ps")
                .then()
                .assertThat()
                .body("login", Matchers.equalTo("andrejs-ps"))            // equalTo()  -> comes from hamcrest. Matchers
                .body("type", Matchers.equalTo("User"));

    }

    @Test
    public void postFails() {

        RestAssured.post("https://api.github.com/user/repos")
                .then()
                .statusCode(401)
                .assertThat()
                // fails - incorrect usage
                .body(Matchers.contains("Requires authentication"));

        // "message":"Requires authentication","documentation_url":"https://developer.github.com/v3/repos/#create"
    }

}
