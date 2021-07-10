import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredTest {
    String URL = "http://api.exchangeratesapi.io/v1/latest?access_key=XXXXXXXXXXXXXXX&symbols=USD,ILS";

    @Test
    public void getBody() {
        float value = get(URL).body().path("rates.ILS");
        System.out.println(value);
    }

    @Test
    public void validateResponse() {
        get(URL).then().assertThat()
                .statusCode(HttpStatus.SC_OK). // make sure response is OK
                body("rates.ILS", equalTo(3.8581f)); // validate value
    }
}
