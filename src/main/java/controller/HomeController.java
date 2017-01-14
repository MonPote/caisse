package controller;

import Service.*;
import Service.CaisseService.*;
import Service.ServicesKit.*;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {
    private int instanceID = 42;
    private String appName = "entrepot";
    private String appip;
    private String stip;

    private ParseFunction fctn = null;


    public HomeController() {
        this.fctn = new ParseFunction("", "");
    }

    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/api/getIp", method = RequestMethod.GET)
    public @ResponseBody String getAppIp() {
        JSONObject jo = new JSONObject();
        jo.put("appName", this.appName);
        jo.put("appip", appip);
        jo.put("stip", stip);
        jo.put("instanceId", instanceID);
        return jo.toString();
    }

    @RequestMapping(value = "/api/setappname", method = RequestMethod.POST)
    public ModelAndView setAppName(HttpServletRequest request) {
        this.appName = request.getParameter("inputappname");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/api/setappip", method = RequestMethod.POST)
    public ModelAndView setAppIp(HttpServletRequest request) {
        this.appip = request.getParameter("inputid");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/api/setstip", method = RequestMethod.POST)
    public ModelAndView setStIp(HttpServletRequest request) {
        this.stip = request.getParameter("inputstid");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/api/setinstanceid", method = RequestMethod.POST)
    public ModelAndView setInstandeId(HttpServletRequest request) {
        this.instanceID = Integer.parseInt(request.getParameter("inputinstanceid"));
        return new ModelAndView("redirect:/");
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
            senderOut = "entrepot";
        }
        System.out.println("Doing handshake");
        /**
         * Creating the agenda
         */
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00", "5", 10,"http://" + this.appip + "/checkFile");
        agendaOut[1] = new Agenda("10:00", "5", 12,"http://" + this.appip + "/checkFile");
        agendaOut[2] = new Agenda("11:00", "5", 13,"http://" + this.appip + "/checkFile");
        Handshake handOut = new Handshake(this.appName, this.instanceID, this.appip, agendaOut);

        /**
         * Running the post method to send the data to the st
         */
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/handshake"),
                new StringEntity("data=" + new Gson().toJson(handOut)));

        return myHttpPost.execute();
    }

    /**
     * Get the message and send a response
     * @param fct
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/msg", method = RequestMethod.POST)
    public @ResponseBody String getMessageGET(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                              @RequestParam(required = true, value = "data", defaultValue = "") String data,
                                              HttpServletRequest request)
            throws ParseException {
        System.out.println("Messages !!!!");
        System.out.println("CALL ON APIMSG !!! AND DATA = " + data);
        String test = "hello";
        fctn.setFct(test);
        System.out.println(data);
        WebService result = new WebService(this.instanceID);
        result.senderSet("Caisse");
        result.dataSet(new TrueData(fctn.execute()));


        Response response = new Response(true, "Every thing works !");
        return "data=" + new Gson().toJson(response);
    }

    /**
     * Get the service and send the result to the st
     * @param fct
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/service", method = RequestMethod.POST)
    public @ResponseBody String getServiceGET(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                              @RequestParam(required = true, value = "data", defaultValue = "") String data,
                                              HttpServletRequest request)
            throws ParseException {
        System.out.println("Service !!!!");
        System.out.println("My FCT = " + fct);

        fctn.setFct(fct);
        fctn.setData(data);

        WebService result = new WebService("Caisse", this.instanceID, new TrueData(fctn.execute()));
        return "data=" + new Gson().toJson(result);
    }

    /**
     * Send file to the st
     * @param targetName
     * @param targetInstance
     * @param fctName
     * @return
     * @throws IOException
     */
    public @ResponseBody String sendFile(String targetName, String targetInstance, String fctName) throws IOException {
        SendFile target = new SendFile(instanceID, "test", this.appName);
        File fileLocation = new File("myfile"); /* FIXME */
        MyHttpPostFile myHttpPostFile = new MyHttpPostFile(new HttpPost("http://" + this.stip + "/api/send_file?fct="
                + fctName + "&target=" + targetName + "&targetInstance=" + targetInstance + "&sender=" + this.appName
                + "&senderInstance=" + this.instanceID),
                new StringEntity("data=" + new Gson().toJson(target)), fileLocation);
        return myHttpPostFile.execute();
    }

    /**
     * Get file from st and store it into the application
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/api/notif_file", method = RequestMethod.POST)
    public @ResponseBody String notifFile(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                        @RequestParam(required = true, value = "data", defaultValue = "") String data,
                                        HttpServletRequest request) throws IOException {
        System.out.println("notif File !!!!!!!!!");
        JSONObject jsonObj = new JSONObject(data);
        System.out.println(data);
        System.out.println(jsonObj.get("fileID"));
        String file = jsonObj.get("fileID").toString().replaceAll("\"", "")
                .replaceAll("[\\[\\]\"]", "").replaceAll(" ", "+");
//        FIXME
//        MyHttpGetFile myHttpGetFile = new MyHttpGetFile("http://" + this.stip + "/api/get_file?target=" + this.appName
//                + "&targetInstance=" + this.instanceID + "&fileID=" + file, "/project/upload-dir/123-", file,
//                Integer.toString(this.instanceID));
//        String result = myHttpGetFile.execute();
        Response response = new Response(true, "Every thing works !");
        System.out.println(response + "5fe6w156 few1f ew1f6w1e6 51w6e1");
        return "data=" + new Gson().toJson(response);
    }

    /*
        TEST PART
     */

    @RequestMapping(value = "/patTest")
    public @ResponseBody String patTestSendMessage(HttpServletRequest request) throws IOException {
        System.out.println("patTest");
        Gson gson = new Gson();

        Produit produit = new Produit();
        List<Produit> list = new ArrayList<Produit>();
        list.add(produit);
        list.add(produit);
        list.add(produit);
        list.add(produit);

        Customer client = new Customer("mode", "fkdsjfkldsjf", list);
        String data = gson.toJson(new CaisseMessage(this.appName, this.instanceID, client));
        System.out.println("myjsonStringDATA = " + data);


        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/service?fct=ticket&target=" + this.appName +"&targetInstance=" + this.instanceID + ""),
                new StringEntity("data=" + data));

        return myHttpPost.execute();
    }

    @RequestMapping(value = "/testMsg")
    public @ResponseBody String testSendMessage(HttpServletRequest request) throws IOException {
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message(this.appName, this.instanceID, trueData));
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/msg?fct=ClientToCA&target=" + this.appName +"&targetInstance=" + this.instanceID + ""),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
    }

    @RequestMapping(value = "/testService")
    public @ResponseBody String testService(HttpServletRequest request) throws IOException {
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message(this.appName, this.instanceID, trueData));
        System.out.println("this.ip = " + this.stip);
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/service?fct=WebService&target=" + this.appName + "&targetInstance=" + this.instanceID + ""),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
    }

    @RequestMapping(value = "/testFile")
    public @ResponseBody String testSendFile() throws IOException {
        TrueData trueData = new TrueData("toto");
        String fctName = "http://" + this.appName + "/checkFile";
        String targetName = this.appName;
        String targetInstance = Integer.toString(this.instanceID);
        Message target = new Message(this.appName, this.instanceID, trueData);
        File fileLocation = new File("/project/upload-dir/hello.txt");
        MyHttpPostFile myHttpPostFile = new MyHttpPostFile(new HttpPost("http://" + this.stip + "/api/send_file?fct=" + fctName + "&target=" + targetName + "&targetInstance=" + targetInstance + "&sender=" + this.appName + "&senderInstance=" + this.instanceID),
                new StringEntity("data=" + new Gson().toJson(target)), fileLocation);
        return myHttpPostFile.execute();
    }

    @RequestMapping(value = "/checkFile")
    public @ResponseBody String checkFile() throws IOException {
        File fileLocation = new File("/project/upload-dir/hello.txt");
        System.out.println(fileLocation);
        File dir = new File("/project");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
        return "{result : " + fileLocation.isFile() + " }";
    }


    @RequestMapping("404")
    public String handlePageNotFound(ModelMap model) {
        return "404";
    }

    public static String encode(String in) {
        String out = in;
        Charset.forName("UTF-8").encode(out);
        return out;
    }
}
