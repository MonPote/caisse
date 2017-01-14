package Service;

import Service.CaisseService.*;
import Service.ServicesKit.MyHttpPost;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Steven on 02/11/2016.
 */
public class ParseFunction {
    String fct = "";
    String data = "";
    Gson gson = new Gson();

    public ParseFunction(String fct, String data) {
        this.fct = fct;
        this.data = data;
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
                return "OK";
            }
            case "ticketToBO": {
                return "ok";
            }
            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
        return "";
    }

    public PurchaseInfo executeTicket() {
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
//                    for (Product product: basket) {
//                        System.out.println("produit = " + product.toString());
//                    }
                    String data = gson.toJson(purchaseInfo);
                    System.out.println("ticket send DATA = " + data);

                    return purchaseInfo; // OK
                }
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
