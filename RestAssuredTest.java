import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredTest {
    String URL = "https://api.apilayer.com/exchangerates_data/convert?to=ILS&from=USD&amount=1";
    @Test
    public void getBody() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .contentType("application/json")
                .header("apikey", "XXXXXXXXXXXXXXXXX")
                .request(Method.GET, URL);

        float value = response.body().path("info.rate");
        System.out.println(value);
    }

    @Test
    public void validateResponse() {

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .contentType("application/json")
                .header("apikey", "XXXXXXXXXXXXXXXXX")
                .request(Method.GET, URL);

        response.then().assertThat()
                .statusCode(HttpStatus.SC_OK). // make sure response is OK
                body("info.rate", equalTo(3.8581f)); // validate value
    }
}
