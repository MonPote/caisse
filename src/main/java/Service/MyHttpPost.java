package Service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by nea on 13/11/16.
 */
public class MyHttpPost {
    HttpPost httpPost;
    StringEntity stringEntity;

    /**
     * Constructor of the class MyHttpPost
     * @param stringEntity
     * @param httpPost
     */
    public MyHttpPost( HttpPost httpPost, StringEntity stringEntity) {
        this.httpPost = httpPost;
        this.stringEntity = stringEntity;
    }

    /**
     * Function that will execute a post function on the st
     * @return
     * @throws IOException
     */
    public String execute () throws IOException {
        /**
         * Creating the httpClient for the post request
         */
        HttpClient httpClient = HttpClientBuilder.create().build();

        /**
         * Creating the http post method
         */
        System.out.println("------ HANDSHAKE Socle tech ------");
        HttpPost httpPost = new HttpPost("http://192.168.0.151/api/handshake");

        /**
         * Cresting the json containing
         */
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Accept","application/json");
        httpPost.setEntity(stringEntity);

        /**
         * Sending the handshake to st
         */
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println("Handshake --> Send");

        /**
         * Print the result of the http request after getted the result form the server
         */
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
            System.out.println(line);
        System.out.println(httpPost.getEntity());

        return httpPost.toString();
    }
}
