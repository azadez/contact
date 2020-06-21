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
    private String CP;
    private String ville;


    protected Adresse() {}

    public Adresse(String Adresse,String CP,String ville) {
        this.Adresse = Adresse; this.CP=CP;this.ville=ville;
    }

    @Override
    public String toString() {
        return String.format(
                "Adresse[id=%d, Adresse='%s', CP='%s', ville='%s']",
                id, Adresse,CP,ville);
    }

    @XmlElement
    public Long getId() {
        return id;
    }
    @XmlElement
    public String getAdresse() {
        return Adresse;
    }
    @XmlElement
    public String getCP(){return CP;}
    @XmlElement
    public String getVille() {
        return ville;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }


    public void setVille(String ville) {
        this.ville = ville;
    }
}
