package Service;

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
            case "clientToCA":
                System.out.println("Je suis bien appelé");
                System.out.println("Et la data = " + data);
                Client client = gson.fromJson(data, Client.class);

                System.out.println("client crée et modePaiement = " + client.getModePaiement() + " carteFid = " + client.getCarteFid());
                List<Produit> panier = client.getPanier();

                for (Produit produit: panier) {
                    System.out.println("code produit = " + produit.getCodeProduit() + " quantity = " + produit.getQuantity());
                }

                break;

            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }

        return new Gson().toJson("msg");
    }
}
