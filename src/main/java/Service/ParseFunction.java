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
            case "ticket":
                System.out.println("Je suis bien appelé");
                System.out.println("Et la data = " + data);
                CaisseData caisseData = gson.fromJson(data, CaisseData.class);
                Client client = caisseData.getData();

                System.out.println("client crée et modePaiement = " + client.getModePaiement() + " carteFid = " + client.getCarteFid());
                List<Produit> panier = client.getPanier();

                for (Produit produit: panier) {
                    System.out.println("code produit = " + produit.getCodeProduit() + " quantity = " + produit.getQuantity());
                }

                if (!client.isValid()) {
                    return gson.toJson("nok");
                } else {
                    return gson.toJson("ok");
                }
            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
        return "";
    }

    public Client caisseExecute() {
        switch (fct) {
            case "ticket":
                CaisseData caisseData = gson.fromJson(data, CaisseData.class);
                Client client = caisseData.getData();

                List<Produit> panier = client.getPanier();

                for (Produit produit: panier) {
                    System.out.println("code produit = " + produit.getCodeProduit() + " quantity = " + produit.getQuantity());
                }

                if (!client.isValid()) {
                    return new Client(); // PAS OK
                } else {
                    return new Client(); // OK
                }
            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
        return new Client();
    }
}
