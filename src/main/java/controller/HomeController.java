package controller;



import Service.*;
import Service.ServicesKit.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import model.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {
    static int instanceID = 44;

    private ProductService productService;
    private StoreService storeService;
    private LocalizationService localizationService;
    private BrandService brandService;
    private CategoryService categoryService;

    ParseFunction fctn = new ParseFunction("", new Data("", new Agenda[0]));
    private String appip = null;

    public HomeController (){
        this.productService = new ProductService();
        this.storeService = new StoreService();
        this.localizationService = new LocalizationService();
        this.brandService = new BrandService();
        this.categoryService = new CategoryService();
    }

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) throws JsonProcessingException {
        return "index";
    }

    @RequestMapping(value = "/api/appip", method = RequestMethod.GET)
    public @ResponseBody String getAppip() {
        JSONObject jo = new JSONObject();
        jo.put("appip", appip);
        return jo.toString();
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

    @RequestMapping(value = "/testService")
    public @ResponseBody String testService(HttpServletRequest request) throws IOException {
        String target = "BO";
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message("BO", 44, trueData));
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://localhost:8000/api/service?fct=WebService&target=BO&targetInstance=44"),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
    }


    /**
     * TEST SECTION
     */
    @RequestMapping(value = "/product/layout", method = RequestMethod.GET)
    public String productLayout(ModelMap model, HttpSession session) {
        return "index";
    }

    @RequestMapping("/productList.json")
    public @ResponseBody String getProductList() {
        List<ProductEntity> list = productService.listProducts();
        int t = list.size();
        for (ProductEntity p : list) {
            p.setBrand(null);
            p.setCategory(null);
            p.setLocalization(null);
            p.setStore(null);
        }
        return new Gson().toJson(list);
    }

    @RequestMapping("/brandList.json")
    public @ResponseBody String getBrandList() {
        List<BrandEntity> list = brandService.listBrands();
        int t = list.size();
        return new Gson().toJson(list);
    }

    @RequestMapping("/categoryList.json")
    public @ResponseBody String getCategoryList() {
        List<CategoryEntity> list = categoryService.listCategories();
        int t = list.size();
        return new Gson().toJson(list);
    }

    @RequestMapping("/storeList.json")
    public @ResponseBody String getStoreList() {
        List<StoreEntity> list = storeService.listStores();
        int t = list.size();
        return new Gson().toJson(list);
    }

    @RequestMapping("/localizationList.json")
    public @ResponseBody String getLocalizationList() {
        List<LocalizationEntity> list = localizationService.listLocalizations();
        int t = list.size();
        return new Gson().toJson(list);
    }

    @RequestMapping(value = "/addProduct/{id}", method = RequestMethod.POST)
    public @ResponseBody void addProduct(@PathVariable("id") String id) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setReference(id);
        productEntity.setId(productService.listProducts().size() + 1);
        productEntity.setDesignation("test");
        productEntity.setQuantity(10);
        productEntity.setStore(storeService.listStores().get(0));
        productEntity.setBrand(brandService.listBrands().get(0));
        productEntity.setCategory(categoryService.listCategories().get(0));
        productEntity.setLocalization(localizationService.listLocalizations().get(0));
        try {
            productService.addProduct(productEntity);
        }
        catch (Exception e) {
        }
    }

    @RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeProduct(@PathVariable("id") String id) {
        ProductEntity productEntity = productService.listProductsById(Integer.parseInt(id)).get(0);
        try {
            productService.removeProduct(productEntity);
        }
        catch (Exception e) {
        }
    }

    @RequestMapping(value = "/addStore/{id}", method = RequestMethod.POST)
    public @ResponseBody void addStore(@PathVariable("id") String id) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setName(id);
        storeEntity.setId(storeService.listStores().size() + 1);
        storeEntity.setAdress("rue test");
        storeEntity.setZipcode("75000");
        try {
            storeService.addStore(storeEntity);
        }
        catch (Exception e) {
        }
    }

    @RequestMapping(value = "/addLocalization/{id}", method = RequestMethod.POST)
    public @ResponseBody void addLocalization(@PathVariable("id") String id) {
        LocalizationEntity localizationEntity = new LocalizationEntity();
        localizationEntity.setName(id);
        localizationEntity.setId(localizationService.listLocalizations().size() + 1);
        try {
            localizationService.addLocalization(localizationEntity);
        }
        catch (Exception e) {
        }
    }

    @RequestMapping(value = "/addBrand/{id}", method = RequestMethod.POST)
    public @ResponseBody void addBrand(@PathVariable("id") String id) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(id);
        brandEntity.setId(brandService.listBrands().size() + 1);
        try {
            brandService.addBrand(brandEntity);
        }
        catch (Exception e) {
        }
    }

    @RequestMapping(value = "/addCategory/{id}", method = RequestMethod.POST)
    public @ResponseBody void addCategory(@PathVariable("id") String id) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(id);
        categoryEntity.setId(categoryService.listCategories().size() + 1);
        try {
            categoryService.addCategory(categoryEntity);
        }
        catch (Exception e) {
        }
    }

    /**
     * Function that will get every data from the database concerning the stock and return it
     * @return json. The Json contain the stock data
     */
    public String generateStockJson() {
        return getProductList();
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
