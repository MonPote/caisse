package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by patrickear on 14/1/2017.
 */
public class Customer {
    private String modePaiement;
    private String carteFid;
    private List<Produit> panier;

    public Customer() {}

    public Customer(String modePaiement, String carteFid, List<Produit> panier) {
        this.modePaiement = modePaiement;
        this.carteFid = carteFid;
        this.panier = panier;
    }

    public String getModePaiement() { return modePaiement; }

    public String getCarteFid() { return carteFid; }

    public List<Produit> getPanier() {
        return panier;
    }

    public boolean isValid() {
        if (modePaiement == null || carteFid == null)
            return false;

        for (Produit produit: panier) {
            if (produit.getCodeProduit() == null || produit.getQuantity() <= 0)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
