package Service.CaisseService;

/**
 * Created by patrickear on 14/1/2017.
 */
public class Produit {
    private String codeProduit;
    private int quantity;

    public Produit() {
        this.codeProduit = "678678";
        this.quantity = 12;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public int getQuantity() {
        return quantity;
    }
}
