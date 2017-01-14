package Service;

import Service.CaisseService.CaisseData;
import Service.CaisseService.Customer;
import Service.CaisseService.Product;
import Service.CaisseService.Produit;
import com.google.gson.Gson;

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
                System.out.println("data = " + data);
                CaisseData caisseData = gson.fromJson(data, CaisseData.class);
                System.out.println(caisseData.toString());

                Customer customer = caisseData.getData();
                List<Produit> panier = customer.getPanier();

                if (!customer.isValid()) {
                    return "nok"; // PAS OK
                } else {
                    // call BO
                    List<Product> basket = panier.stream()
                            .map(produit -> new Product(produit.getCodeProduit(), produit.getQuantity()))
                            .collect(Collectors.toList());

                    for (Product product: basket) {
                        System.out.println("produit = " + product.toString());
                    }
                    return "ok"; // OK
                }
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
}
