package model.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Xavier on 30/09/2016.
 */
@Entity
@Table(name = "Command", schema = "Back Office", catalog = "postgres")
public class CommandEntity {
    private Serializable id;
    private String ref;
    private Date dateEnvoi;
    private Date dateReception;
    private Serializable idClient;

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
    @Column(name = "Date_envoi", nullable = false)
    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Basic
    @Column(name = "Date_reception", nullable = true)
    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    @Basic
    @Column(name = "id_client", nullable = true)
    public Serializable getIdClient() {
        return idClient;
    }

    public void setIdClient(Serializable idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandEntity that = (CommandEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
        if (dateEnvoi != null ? !dateEnvoi.equals(that.dateEnvoi) : that.dateEnvoi != null) return false;
        if (dateReception != null ? !dateReception.equals(that.dateReception) : that.dateReception != null)
            return false;
        if (idClient != null ? !idClient.equals(that.idClient) : that.idClient != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        result = 31 * result + (dateEnvoi != null ? dateEnvoi.hashCode() : 0);
        result = 31 * result + (dateReception != null ? dateReception.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        return result;
    }
}
