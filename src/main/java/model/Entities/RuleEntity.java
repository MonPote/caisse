package model.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Rule", schema = "Back Office", catalog = "postgres")
public class RuleEntity {
    private Serializable id;
    private Serializable montant;
    private Serializable resultat;
    private Serializable unit;

    @Id
    @Column(name = "id", nullable = false)
    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Montant", nullable = false)
    public Serializable getMontant() {
        return montant;
    }

    public void setMontant(Serializable montant) {
        this.montant = montant;
    }

    @Basic
    @Column(name = "Resultat", nullable = true)
    public Serializable getResultat() {
        return resultat;
    }

    public void setResultat(Serializable resultat) {
        this.resultat = resultat;
    }

    @Basic
    @Column(name = "Unit", nullable = true)
    public Serializable getUnit() {
        return unit;
    }

    public void setUnit(Serializable unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleEntity that = (RuleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (montant != null ? !montant.equals(that.montant) : that.montant != null) return false;
        if (resultat != null ? !resultat.equals(that.resultat) : that.resultat != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (montant != null ? montant.hashCode() : 0);
        result = 31 * result + (resultat != null ? resultat.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
