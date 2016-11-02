package model.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Product", schema = "Back Office", catalog = "postgres")
public class ProductEntity {
    private Serializable id;
    private String ref;
    private String designation;
    private Serializable quantite;
    private Serializable prix;
    private Serializable stockMin;
    private Serializable stockMax;

    @Id
    @Column(name = "id", nullable = false)
    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Ref", nullable = false, length = 32)
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Basic
    @Column(name = "Designation", nullable = false, length = 64)
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
    @Column(name = "Prix", nullable = false)
    public Serializable getPrix() {
        return prix;
    }

    public void setPrix(Serializable prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "Stock_min", nullable = false)
    public Serializable getStockMin() {
        return stockMin;
    }

    public void setStockMin(Serializable stockMin) {
        this.stockMin = stockMin;
    }

    @Basic
    @Column(name = "Stock_max", nullable = false)
    public Serializable getStockMax() {
        return stockMax;
    }

    public void setStockMax(Serializable stockMax) {
        this.stockMax = stockMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
        if (designation != null ? !designation.equals(that.designation) : that.designation != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;
        if (prix != null ? !prix.equals(that.prix) : that.prix != null) return false;
        if (stockMin != null ? !stockMin.equals(that.stockMin) : that.stockMin != null) return false;
        if (stockMax != null ? !stockMax.equals(that.stockMax) : that.stockMax != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (stockMin != null ? stockMin.hashCode() : 0);
        result = 31 * result + (stockMax != null ? stockMax.hashCode() : 0);
        return result;
    }
}
