package fr.sopra.mobile.bssplug;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ntakpe_j
 */
@Entity
public class JsonData {

    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String service;

    @NotEmpty
    @Column(columnDefinition = "LONGTEXT")
    private String json;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JsonData jsonData = (JsonData) o;

        if (id != null ? !id.equals(jsonData.id) : jsonData.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "id='" + id + '\'' +
                ", service='" + service + '\'' +
                ", json='" + json + '\'' +
                '}';
    }
}
