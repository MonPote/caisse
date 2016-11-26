package controller;



import Service.*;
import Service.ServicesKit.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {
    static int instanceID = 44;

    ParseFunction fctn = new ParseFunction("", new Data("", new Agenda[0]));

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) throws JsonProcessingException {
        return "index";
    }

    /**
     * Handshake function
     * @return
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @RequestMapping(value = "/api/handshake", method = RequestMethod.GET)
    public @ResponseBody String handshake() throws IOException, org.json.simple.parser.ParseException {
        String senderOut = System.getenv("AppName");
        if (senderOut == null) {
            senderOut = "BO";
        }

        int instanceOut = instanceID;

        /**
         * Get the ip adress of the current host
         */
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

        /**
         * Creating the agenda
         */
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, "WebService");
        agendaOut[1] = new Agenda("10:00", 5, "WebService");
        agendaOut[2] = new Agenda("11:00", 5, "WebService");
        Handshake handOut = Handshake.getInstance(senderOut, instanceOut, addressOut, agendaOut);

        /**
         * Running the post method to send the data to the st
         */
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://localhost:8000/api/handshake"),
                new StringEntity("data=" + new Gson().toJson(handOut)));

        return myHttpPost.execute();
    }

    /**
     * Send Agenda function
     * @return
     */
    @RequestMapping(value = "/api/sendAgenda", method = RequestMethod.GET)
    public @ResponseBody String sendAgenda() {
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, "WebService");
        agendaOut[1] = new Agenda("10:00", 5, "WebService");
        agendaOut[2] = new Agenda("11:00", 5, "WebService");
        return new Gson().toJson(agendaOut);
    }

    /**
     * Get the name of the function to call (WebService)
     * @param fct
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/msg", method = RequestMethod.GET)
    public @ResponseBody String getMessageGET(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                              @RequestParam(required = true, value = "sender", defaultValue = "") String sender,
                                              HttpServletRequest request)
            throws ParseException {
        System.out.println("Messages !!!!");
        fctn.setFct(fct);
        WebService result = new WebService(this.instanceID);
        result.senderSet(sender);
        result.dataSet(new TrueData(fctn.execute()));
        return new Gson().toJson(result);
    }

    /**
     * Unused Function
     * @param data
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/msg", method = RequestMethod.POST)
    public @ResponseBody String getMessagePOST(@RequestParam(required = true, value = "data", defaultValue = "") String data)
            throws ParseException {
        System.out.println("/api/msg/post: " + data.toString());
        Response response = new Response(true, "Every thing works !");
        return "data=" + new Gson().toJson(response);
    }

    @RequestMapping(value = "/sendFile", method = RequestMethod.GET)
    public @ResponseBody String sendFile(@RequestParam(required = true, value = "fct", defaultValue = "") String fct, HttpServletRequest request) throws IOException {
        SendFile target = new SendFile(instanceID, "test", "BO");
        String fileLocation = "myfile";
        MyHttpPostFile myHttpPostFile = new MyHttpPostFile(new HttpPost("http://192.168.0.151/send_file"),
                new StringEntity("data=" + new Gson().toJson(target)), new File(fileLocation));

        return myHttpPostFile.execute();
    }

    @RequestMapping(value = "/notif_file", method = RequestMethod.GET)
    public @ResponseBody String getFile(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                        @RequestParam(value = "fileID", defaultValue = "") String fileId,
                                        HttpServletRequest request) throws IOException {
        String target = "BO";
        MyHttpGetFile myHttpGetFile = new MyHttpGetFile("http://192.168.0.151/get_file?target=" + target
                + "&fct=" + fct + "&instance=" + this.instanceID + "&fielid=" + fileId, "");
        return myHttpGetFile.execute();
    }

    @RequestMapping(value = "/testMsg")
    public @ResponseBody String testSendMessage(HttpServletRequest request) throws IOException {
        String target = "BO";
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message("BO", 44, trueData));
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://localhost:8000/api/msg?fct=WebService&target=BO&targetInstance=44"),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
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
