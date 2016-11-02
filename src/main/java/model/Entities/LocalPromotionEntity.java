package model.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Local_promotion", schema = "Back Office", catalog = "postgres")
public class LocalPromotionEntity {
    private Serializable id;
    private String ref;
    private String designation;
    private Serializable idRegle;
    private Date dateStart;
    private Date dateStop;
    private Serializable recurence;

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
    @Column(name = "id_regle", nullable = false)
    public Serializable getIdRegle() {
        return idRegle;
    }

    public void setIdRegle(Serializable idRegle) {
        this.idRegle = idRegle;
    }

    @Basic
    @Column(name = "Date_start", nullable = false)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "Date_stop", nullable = false)
    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    @Basic
    @Column(name = "Recurence", nullable = false)
    public Serializable getRecurence() {
        return recurence;
    }

    public void setRecurence(Serializable recurence) {
        this.recurence = recurence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalPromotionEntity that = (LocalPromotionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
        if (designation != null ? !designation.equals(that.designation) : that.designation != null) return false;
        if (idRegle != null ? !idRegle.equals(that.idRegle) : that.idRegle != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateStop != null ? !dateStop.equals(that.dateStop) : that.dateStop != null) return false;
        if (recurence != null ? !recurence.equals(that.recurence) : that.recurence != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (idRegle != null ? idRegle.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateStop != null ? dateStop.hashCode() : 0);
        result = 31 * result + (recurence != null ? recurence.hashCode() : 0);
        return result;
    }
}
