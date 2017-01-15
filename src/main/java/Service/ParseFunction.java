package Service;

import Service.CaisseService.*;
import Service.ServicesKit.MyHttpPost;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Steven on 02/11/2016.
 */
public class ParseFunction {
    private String appName;
    private int instanceID;
    private String appip;
    private String stip;

    private String fct = "";
    private String data = "";
    private Gson gson = new Gson();

    public ParseFunction(String fct, String data) {
        this.fct = fct;
        this.data = data;
    }

    public ParseFunction(String appName, int instanceID, String appip, String stip) {
        this.appName = appName;
        this.instanceID = instanceID;
        this.appip = appip;
        this.stip = stip;
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String execute() {
        switch (fct) {
            case "ticket": {
                System.out.println("Inside ticket");
                System.out.println("data = " + data);

                CaisseData caisseData = gson.fromJson(data, CaisseData.class);
                Customer customer = caisseData.getData();
                List<Produit> panier = customer.getPanier();

                if (!customer.isValid()) {
                    return "KO"; // PAS OK
                } else {
                    List<Product> basket = panier.stream()
                            .map(produit -> new Product(produit.getCodeProduit(), produit.getQuantity()))
                            .collect(Collectors.toList());

                    PurchaseInfo purchaseInfo = new PurchaseInfo("0", "", basket);
                    CaisseWebService result = new CaisseWebService("Caisse", this.instanceID, purchaseInfo);
                    String newData = new Gson().toJson(result);

                    // call BO
                    sendMsg("Caisse", "1", newData);
                    return "OK";
                }
            }
            case "productsToCaisse": {

                return "KO";
            }
            case "ticketToBO": {
                System.out.println("Inside ticketToBO");
                System.out.println("data = " + data);
                return "OK";
            }
            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
        return "";
    }

    private void sendMsg(String target, String targetInstance, String newData) {
        try {
            String targetUrl = "http://" + this.stip + "/api/service?fct=ticketToBO&target="
                    + target +"&targetInstance=" + targetInstance + "";
            System.out.println("targetUrl = " + targetUrl);
            MyHttpPost myHttpPost = new MyHttpPost(new HttpPost(targetUrl), new StringEntity("data=" + newData));
            myHttpPost.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CaisseJson executeTicket() {
        switch (fct) {
            case "ticket": {
                System.out.println("Inside ticket");
                System.out.println(data);
                CaisseData caisseData = gson.fromJson(data, CaisseData.class);

                Customer customer = caisseData.getData();
                List<Produit> panier = customer.getPanier();

                if (!customer.isValid()) {
                    return null; // PAS OK
                } else {
                    // call BO
                    List<Product> basket = panier.stream()
                            .map(produit -> new Product(produit.getCodeProduit(), produit.getQuantity()))
                            .collect(Collectors.toList());

                    PurchaseInfo purchaseInfo = new PurchaseInfo("0", "", basket);

                    String data = gson.toJson(purchaseInfo);
                    System.out.println("ticket send DATA = " + data);

                    return purchaseInfo; // OK
                }
            }
            case "productsToCaisse": {

                return null;
            }
            case "ticketToBO": {
                System.out.println("Inside ticketToBO");
                System.out.println(data);
                return null;
            }
            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
        return null;
    }
}
