package controller;

import Service.Data;
import Service.Handshake;
import Service.HandshakeConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) throws JsonProcessingException {
        String senderOut = "sender";
        String instanceOut = "instanceID";
        String addressOut = "adress";
        String[] agendaOut = {"truc", "bidule"};
        //Data dataOut = new Data(addressOut, agendaOut);
        Handshake handOut = Handshake.getInstance(senderOut, instanceOut, addressOut, agendaOut);
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        System.out.println("1 -> " + gson.toJson(handOut));
        HandshakeConnection firtStart = new HandshakeConnection();

        try {
            firtStart.post("http://st/api/handshake", gson.toJson(handOut));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str ="une string";
        model.addAttribute("strg", str);

        return "home";
    }
}
