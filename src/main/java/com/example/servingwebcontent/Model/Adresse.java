package com.example.servingwebcontent.Model;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Entity
@Transactional
@XmlRootElement(name="adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String Adresse;


    protected Adresse() {}

    public Adresse(String Adresse) {
        this.Adresse = Adresse;
    }

    @Override
    public String toString() {
        return String.format(
                "Adresse[id=%d, Adresse='%s']",
                id, Adresse);
    }

    @XmlElement
    public Long getId() {
        return id;
    }
    @XmlElement
    public String getAdresse() {
        return Adresse;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

}
