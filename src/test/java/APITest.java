import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class APITest {

    private static class Pokemon {
        String name;
        String url;
    }

    private static class Pokemons {
        int count;
        String next;
        String previous;
        List<Pokemon> results;
    }

    @Test
    public void httpTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet("https://pokeapi.co/api/v2/pokemon");

            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpResponse response = httpClient.execute(request);
            try {
                Assert.assertEquals(response.getCode(), 200);

                HttpEntity entity = response.getEntity();
                Assert.assertNotNull(entity);

                // simple check
                String jsonString = EntityUtils.toString(entity);
                Assert.assertTrue(jsonString.contains("\"count\":1118"));

                // regular check
                Pokemons pokemons = new Gson().fromJson(jsonString, Pokemons.class);
                Assert.assertEquals(pokemons.count, 1118);
                Assert.assertEquals(pokemons.results.size(), 20);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    @Test
    public void restAssuredTest() {
        RestAssured.when().get("https://pokeapi.co/api/v2/pokemon").
                then().
                statusCode(200).
                body("count", Matchers.equalTo(1118),
                        "results.name", Matchers.hasItems("bulbasaur", "ivysaur"));
    }
}