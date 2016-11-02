package model.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Product_coupon", schema = "Back Office", catalog = "postgres")
@IdClass(ProductCouponEntityPK.class)
public class ProductCouponEntity {
    private Serializable idProduit;
    private Serializable idCoupon;

    @Id
    @Column(name = "id_produit", nullable = false)
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Id
    @Column(name = "id_coupon", nullable = false)
    public Serializable getIdCoupon() {
        return idCoupon;
    }

    public void setIdCoupon(Serializable idCoupon) {
        this.idCoupon = idCoupon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCouponEntity that = (ProductCouponEntity) o;

        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (idCoupon != null ? !idCoupon.equals(that.idCoupon) : that.idCoupon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduit != null ? idProduit.hashCode() : 0;
        result = 31 * result + (idCoupon != null ? idCoupon.hashCode() : 0);
        return result;
    }
}
