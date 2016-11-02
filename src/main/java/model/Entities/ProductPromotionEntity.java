package model.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Product_promotion", schema = "Back Office", catalog = "postgres")
@IdClass(ProductPromotionEntityPK.class)
public class ProductPromotionEntity {
    private Serializable idProduit;
    private Serializable idPromotion;

    @Id
    @Column(name = "id_produit", nullable = false)
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Id
    @Column(name = "id_promotion", nullable = false)
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

        ProductPromotionEntity that = (ProductPromotionEntity) o;

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
