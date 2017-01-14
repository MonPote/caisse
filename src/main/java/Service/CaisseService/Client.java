package Service.CaisseService;

import java.util.List;

/**
 * Created by patrickear on 14/1/2017.
 */
public class Client {
    private String modePaiement;
    private String carteFid;
    private List<Produit> panier;

    public String getModePaiement() { return modePaiement; }

    public String getCarteFid() { return carteFid; }

    public List<Produit> getPanier() {
        return panier;
    }
}
