import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RestAPITest {
    public static void main(String[] args) throws IOException, JSONException {
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=ILS&from=USD&amount=1")
                .addHeader("apikey", "XXXXXXXXXXXXXXXX")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        response.close();
        // parse JSON
        JSONObject mainJsonObject = new JSONObject(jsonData);
        // get Json object
        JSONObject resultsJson = mainJsonObject.getJSONObject("info");
        // get value
        double val = resultsJson.getDouble("rate");
        System.out.println(val);
    }
}
