package com.example.servingwebcontent.Model;


import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Transactional
@XmlRootElement(name="email")
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "Mail", unique = true)
    private String Mail;


    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;


    protected Email() {}

    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email(String Email) {
        this.Mail = Email.replace(",", "");
    }

    @XmlElement
    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    @Override
    public String toString() {
        return String.format(
                "Email[id=%d, Email='%s']",
                id, Mail);
    }
    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
