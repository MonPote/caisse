package model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
public class ProductPromotionEntityPK implements Serializable {
    private Serializable idProduit;
    private Serializable idPromotion;

    @Column(name = "id_produit", nullable = false)
    @Id
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Column(name = "id_promotion", nullable = false)
    @Id
    public Serializable getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Serializable idPromotion) {
        this.idPromotion = idPromotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPromotionEntityPK that = (ProductPromotionEntityPK) o;

        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (idPromotion != null ? !idPromotion.equals(that.idPromotion) : that.idPromotion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduit != null ? idProduit.hashCode() : 0;
        result = 31 * result + (idPromotion != null ? idPromotion.hashCode() : 0);
        return result;
    }
}
