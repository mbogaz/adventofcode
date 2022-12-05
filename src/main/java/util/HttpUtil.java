package util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String httpGetAsString(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);

        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }
}
