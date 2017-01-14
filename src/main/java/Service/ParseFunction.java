package Service;

import Service.CaisseService.CaisseData;
import Service.CaisseService.Client;
import Service.CaisseService.Produit;
import com.google.gson.Gson;

import java.util.List;

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

                Client client = caisseData.getData();
                List<Produit> panier = client.getPanier();

                for (Produit produit: panier) {
                    System.out.println("code produit = " + produit.getCodeProduit() + " quantity = " + produit.getQuantity());
                }

                if (!client.isValid()) {
                    return "nok"; // PAS OK
                } else {
                    // call BO

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
