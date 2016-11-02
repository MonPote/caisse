package controller;

import Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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

    @RequestMapping(value = "/api/handshake", method = RequestMethod.GET)
    public @ResponseBody String handshake() {
        String senderOut = "sender";
        String instanceOut = "instanceID";
        String addressOut = "adress";
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, 3);
        agendaOut[1] = new Agenda("10:00", 5, 3);
        agendaOut[2] = new Agenda("11:00", 5, 3);
        //Data dataOut = new Data(addressOut, agendaOut);
        Handshake handOut = Handshake.getInstance(senderOut, instanceOut, addressOut, agendaOut);
        return new Gson().toJson(handOut);
    }

    @RequestMapping(value = "/api/sendAgenda", method = RequestMethod.GET)
    public @ResponseBody String sendAgenda() {
        Agenda agenda = new Agenda("09:00", 5, 3);
        //Data dataOut = new Data(addressOut, agendaOut);
        return new Gson().toJson(agenda);
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
        //(HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty( "Content-type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "Accept", "*/*" );
//        String encoded = new String(Base64.encode(userpass.getBytes()));
//        conn.setRequestProperty("Authorization", "Basic " + encoded);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
