package controller;

import Service.Data;
import Service.Handshake;
import Service.HandshakeConnection;
import Service.ParseFunction;
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
import java.io.File;
import java.io.IOException;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {

    ParseFunction fctn = new ParseFunction("", new Data("", new String[0][0]));

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) throws JsonProcessingException {
        return "index";
    }

    @RequestMapping(value = "/api/handshake", method = RequestMethod.GET)
    public @ResponseBody String handshake() {
        String senderOut = "sender";
        String instanceOut = "instanceID";
        String addressOut = "adress";
        String[][] agendaOut = {{"date", "repeat", "callback"},{"09:00", "5", "..."}};
        //Data dataOut = new Data(addressOut, agendaOut);
        Handshake handOut = Handshake.getInstance(senderOut, instanceOut, addressOut, agendaOut);
        return new Gson().toJson(handOut);
    }

    @RequestMapping(value = "/app/api/msg", method = RequestMethod.GET)
    public void getMessageGET(
            @RequestParam(required = true) String fct,
            BindingResult result, HttpServletRequest request)
            throws ParseException {
                fctn.setFct(fct);
    }

    @RequestMapping(value = "/app/api/msg", method = RequestMethod.POST)
    public void getMessagePOST(
            @PathVariable("Data") Data data,
            BindingResult result, HttpServletRequest request)
            throws ParseException {
                fctn.setData(data);
                fctn.execute();
    }
}
