package model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
public class CommandDetailEntityPK implements Serializable {
    private Serializable idCommande;
    private Serializable idProduit;

    @Column(name = "id_commande", nullable = false)
    @Id
    public Serializable getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Serializable idCommande) {
        this.idCommande = idCommande;
    }

    @Column(name = "id_produit", nullable = false)
    @Id
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandDetailEntityPK that = (CommandDetailEntityPK) o;

        if (idCommande != null ? !idCommande.equals(that.idCommande) : that.idCommande != null) return false;
        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommande != null ? idCommande.hashCode() : 0;
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        return result;
    }
}
