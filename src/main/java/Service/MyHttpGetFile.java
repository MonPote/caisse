package Service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by nea on 13/11/16.
 */
public class MyHttpGetFile {
    String url;
    String target;

    /**
     * Constructor of the class MyHttpPost
     * @param url
     * @param target
     */
    public MyHttpGetFile(String url, String target) {
        this.url = url;
        this.target = target;
    }

    /**
     * Function that will execute a post function on the st
     * @return
     * @throws IOException
     */
    public String execute() throws IOException {
        URL url = new URL(this.url);

        String fileName = url.getFile();

        Path targetPath = new File(target + fileName).toPath();

        Files.copy(url.openStream(), targetPath,
                StandardCopyOption.REPLACE_EXISTING);

        return "ok";
    }
}
