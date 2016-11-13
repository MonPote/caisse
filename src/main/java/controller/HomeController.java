package controller;



import Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.HttpClient;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {

    ParseFunction fctn = new ParseFunction("", new Data("", new Agenda[0]));

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) throws JsonProcessingException {
        return "index";
    }

    @RequestMapping(value = "/api/handshake/toto")
    public @ResponseBody String hand(@RequestParam(value="data",defaultValue = "")String data) {
        System.out.println("Handshake data -------> " + data);
        System.out.println("Handshake Received !!!!");
        return "tot";
    }

    @RequestMapping(value = "/api/handshake", method = RequestMethod.GET)
    public @ResponseBody String handshake() throws IOException, org.json.simple.parser.ParseException {
        String senderOut = "BO";
        int instanceOut = 4;

        String addressMix = null;
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        addressMix = ip.toString();
        String[] parts = addressMix.split("/");
        String addressOut = parts[parts.length - 1];


        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, 3);
        agendaOut[1] = new Agenda("10:00", 5, 3);
        agendaOut[2] = new Agenda("11:00", 5, 3);
        Handshake handOut = Handshake.getInstance(senderOut, instanceOut, addressOut, agendaOut);
        HttpClient httpClient = HttpClientBuilder.create().build();

        System.out.println("------ HANDSHAKE Socle tech ------");
        HttpPost httpPost = new HttpPost("http://192.168.0.151/api/handshake");
        StringEntity params =new StringEntity("data=" + new Gson().toJson(handOut));
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Accept","application/json");
        httpPost.setEntity(params);
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println("Json -----> " + new Gson().toJson(handOut));
        //test that the function works
        System.out.println("Handshake --> Send");
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(httpPost.getEntity());

        return httpPost.toString();
    }

    @RequestMapping(value = "/api/sendAgenda", method = RequestMethod.GET)
    public @ResponseBody String sendAgenda() {
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, 3);
        agendaOut[1] = new Agenda("10:00", 5, 3);
        agendaOut[2] = new Agenda("11:00", 5, 3);
        return new Gson().toJson(agendaOut);
    }

    @RequestMapping(value = "/api/msg", method = RequestMethod.GET)
    public @ResponseBody String getMessageGET(@RequestParam(required = true) String fct, HttpServletRequest request)
            throws ParseException {
        fctn.setFct(fct);
        return fct;
    }

    @RequestMapping(value = "/api/msg", method = RequestMethod.POST)
    public @ResponseBody String getMessagePOST(@PathVariable("Data") Data data, HttpServletRequest request)
            throws ParseException {
        fctn.setData(data);
        fctn.execute();
        return data.toString();
    }


    // requestMethod : "GET"
// url exemple : "http://localhost:8080/"
    private static String getReponse(final String urlToRead, String requestMethod) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn;
        conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty( "Content-type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "Accept", "*/*" );
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
