package Service.ServicesKit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nea on 13/11/16.
 */
public class MyHttpPostFile {
    HttpPost httpPost;
    StringEntity stringEntity;
    File file;

    /**
     * Constructor of the class MyHttpPost
     * @param stringEntity
     * @param httpPost
     */
    public MyHttpPostFile(HttpPost httpPost, StringEntity stringEntity, File file) {
        this.httpPost = httpPost;
        this.stringEntity = stringEntity;
        this.file = file;
    }

    /**
     * Function that will execute a post function on the st
     * @return
     * @throws IOException
     */
    public String execute() throws IOException {
        /**
         * Creating the httpClient for the post request
         */
        HttpClient httpClient = HttpClientBuilder.create().build();

        /**
         * Cresting the json containing
         */
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Accept","application/json");
        httpPost.setEntity(stringEntity);

        FileBody uploadFilePart = new FileBody(file);

        /**
         * Creating the httpClient for the post request
         */
        HttpEntity entity = MultipartEntityBuilder.create()
                .addPart("file", uploadFilePart)
                .build();
        httpPost.setEntity(entity);

        /**
         * Sending the handshake to st
         */
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println("SendFile --> Send");

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
