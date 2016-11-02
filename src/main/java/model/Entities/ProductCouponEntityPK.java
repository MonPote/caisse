package model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
public class ProductCouponEntityPK implements Serializable {
    private Serializable idProduit;
    private Serializable idCoupon;

    @Column(name = "id_produit", nullable = false)
    @Id
    public Serializable getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Serializable idProduit) {
        this.idProduit = idProduit;
    }

    @Column(name = "id_coupon", nullable = false)
    @Id
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

        ProductCouponEntityPK that = (ProductCouponEntityPK) o;

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
