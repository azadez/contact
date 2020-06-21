package com.example.servingwebcontent.Model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Transactional
@XmlRootElement(name="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;

    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="ADRESSES")
    private Collection<Adresse> adresses;



    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Email> emails;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    @XmlElement
    public Long getId() {
        return id;
    }
    @XmlElement
    public String getFirstName() {
        return firstName;
    }
    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String str){firstName=str;}
    public void setLastName(String str){lastName=str;}

    public void setId(Long id) {
        this.id = id;
    }
    @XmlTransient
    public Collection<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(Collection<Adresse> adresses) {
        this.adresses = adresses;
    }
    @XmlTransient
    public Collection<Email> getEmails() {
        return emails;
    }

    public void setEmails(Collection<Email> emails) {
        this.emails = emails;
    }


}