package model.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Command_detail", schema = "Back Office", catalog = "postgres")
@IdClass(CommandDetailEntityPK.class)
public class CommandDetailEntity {
    private Serializable idCommande;
    private Serializable idProduit;
    private Serializable quantite;
    private Serializable pertes;

    @Id
    @Column(name = "id_commande", nullable = false)
    public Serializable getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Serializable idCommande) {
        this.idCommande = idCommande;
    }

    @Id
    @Column(name = "id_produit", nullable = false)
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Basic
    @Column(name = "Quantite", nullable = false)
    public Serializable getQuantite() {
        return quantite;
    }

    public void setQuantite(Serializable quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "Pertes", nullable = false)
    public Serializable getPertes() {
        return pertes;
    }

    public void setPertes(Serializable pertes) {
        this.pertes = pertes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandDetailEntity that = (CommandDetailEntity) o;

        if (idCommande != null ? !idCommande.equals(that.idCommande) : that.idCommande != null) return false;
        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;
        if (pertes != null ? !pertes.equals(that.pertes) : that.pertes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommande != null ? idCommande.hashCode() : 0;
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + (pertes != null ? pertes.hashCode() : 0);
        return result;
    }
}
