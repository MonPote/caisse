package controller;

import Service.*;
import Service.ServicesKit.*;
import com.google.gson.Gson;
import model.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;


/**
 * Created by claebo_c on 13/09/16.
 */
@Controller
public class HomeController {
    private int instanceID = 44;
    private String appName = "BO";
    private String appip = null;
    private String stip = null;

    private ProductService productService;
    private StoreService storeService;
    private LocalizationService localizationService;
    private BrandService brandService;
    private CategoryService categoryService;
    private ParseFunction fctn = null;


    public HomeController() {
        this.productService = new ProductService();
        this.storeService = new StoreService();
        this.localizationService = new LocalizationService();
        this.brandService = new BrandService();
        this.categoryService = new CategoryService();

        this.fctn = new ParseFunction("", new Data("", new Agenda[0]));
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
            senderOut = "BO";
        }

        /**
         * Creating the agenda
         */
        Agenda[] agendaOut = new Agenda[3];
        agendaOut[0] = new Agenda("09:00",5, "WebService");
        agendaOut[1] = new Agenda("10:00", 5, "WebService");
        agendaOut[2] = new Agenda("11:00", 5, "WebService");
        Handshake handOut = Handshake.getInstance(this.appName, this.instanceID, this.appip, agendaOut);

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
     * @param instanceID
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/msg", method = RequestMethod.GET)
    public @ResponseBody String getMessageGET(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                              @RequestParam(required = true, value = "sender", defaultValue = "") String sender,
                                              @RequestParam(required = true, value = "instanceID", defaultValue = "")String instanceID,
                                              HttpServletRequest request)
            throws ParseException {
        System.out.println("Messages !!!!");
        Response response = new Response(true, "Every thing works !");
        return "data=" + new Gson().toJson(response);
    }

    /**
     * Get the service and send the result to the st
     * @param fct
     * @param sender
     * @param instanceID
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/api/service", method = RequestMethod.GET)
    public @ResponseBody String getServiceGET(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                              @RequestParam(required = true, value = "sender", defaultValue = "") String sender,
                                              @RequestParam(required = true, value = "instanceID", defaultValue = "")String instanceID,
                                              HttpServletRequest request)
            throws ParseException {
        System.out.println("Service !!!!");
        fctn.setFct(fct);
        WebService result = new WebService(this.instanceID);
        result.senderSet(sender);
        result.dataSet(new TrueData(fctn.execute()));
        return new Gson().toJson(result);
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
        SendFile target = new SendFile(instanceID, "test", "BO");
        File fileLocation = new File("myfile"); /* FIXME */
        MyHttpPostFile myHttpPostFile = new MyHttpPostFile(new HttpPost("http://" + this.stip + "/send_file?fct="
                + fctName + "&target=" + targetName + "&targetInstance=" + targetInstance + ""),
                new StringEntity("data=" + new Gson().toJson(target)), fileLocation);
        return myHttpPostFile.execute();
    }

    /**
     * Get file from st and store it into the application
     * @param fct
     * @param app
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/notif_file", method = RequestMethod.GET)
    public @ResponseBody String notifFile(@RequestParam(required = true, value = "fct", defaultValue = "") String fct,
                                        @RequestParam(value = "sender", defaultValue = "") String app,
                                        @RequestParam(value = "targetInstance", defaultValue = "") String targetInstance,
                                        @RequestParam(value = "fileID", defaultValue = "") MultipartFile file,
                                        HttpServletRequest request) throws IOException {
        System.out.println("notif File !!!!");
        MyHttpGetFile myHttpGetFile = new MyHttpGetFile("http://" + this.stip + "/get_file?target=" + app
                + "&instance=" + targetInstance + "&fielid=" + file, "");
        String result = myHttpGetFile.execute();
        getFile(file, result, targetInstance);
        Response response = new Response(true, "Every thing works !");
        return "data=" + new Gson().toJson(response);
    }

    /**
     * Function that store the receptioned file
     * @param file
     * @param content
     * @param targetInstance
     * @return
     * @throws IOException
     */
    public String getFile(MultipartFile file, String content, String targetInstance) throws IOException {
        System.out.println("getFile !!!!");
        FileHandler fileHandler = new FileHandler(file);
        fileHandler.store(file, targetInstance);
        Response response = new Response(true, "Every thing works !");
        return "data=" + new Gson().toJson(response);
    }

    /*
        TEST PART
     */

    @RequestMapping(value = "/testMsg")
    public @ResponseBody String testSendMessage(HttpServletRequest request) throws IOException {
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message(this.appName, this.instanceID, trueData));
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/msg?fct=WebService&target=" + this.appName +"&targetInstance=" + this.instanceID + ""),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
    }

    @RequestMapping(value = "/testService")
    public @ResponseBody String testService(HttpServletRequest request) throws IOException {
        TrueData trueData = new TrueData("toto");
        String data = new Gson().toJson(new Message(this.appName, this.instanceID, trueData));
        MyHttpPost myHttpPost = new MyHttpPost(new HttpPost("http://" + this.stip + "/api/service?fct=WebService&target=" + this.appName + "&targetInstance=" + this.instanceID + ""),
                new StringEntity("data=" + data));
        return myHttpPost.execute();
    }

    @RequestMapping(value = "/testFile")
    public @ResponseBody String testSendFile() throws IOException {
        String fctName = "test";
        String targetName = this.appName;
        String targetInstance = Integer.toString(this.instanceID);
        SendFile target = new SendFile(instanceID, fctName, this.appName);
        File fileLocation = new File("upload-dir/hello.txt");
        MyHttpPostFile myHttpPostFile = new MyHttpPostFile(new HttpPost("http://" + this.stip + "/send_file?fct=" + fctName + "&target=" + targetName + "&targetInstance=" + targetInstance + ""),
                new StringEntity("data=" + new Gson().toJson(target)), fileLocation);
        return myHttpPostFile.execute();
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
