package model;

import javax.persistence.*;

/**
 * Created by nea on 30/09/16.
 */
@Entity
@Table(name = "product", schema = "public", catalog = "entrepot")
public class ProductEntity implements java.io.Serializable {
    private int id;
    private String reference;
    private String designation;
    private int quantity;
    private StoreEntity store;
    private LocalizationEntity localization;
    private CategoryEntity category;
    private BrandEntity brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    public StoreEntity getStore() {
        return this.store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localization_id", nullable = false)
    public LocalizationEntity getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationEntity localization) {
        this.localization = localization;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reference", unique = true)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Basic
    @Column(name = "designation")
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (reference != null ? !reference.equals(that.reference) : that.reference != null) return false;
        if (designation != null ? !designation.equals(that.designation) : that.designation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
