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

    @Column(unique = true)
    private String Email;


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
    @XmlElement
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Email(String Email) {
        this.Email = Email.replace(",","");
    }

    @Override
    public String toString() {
        return String.format(
                "Email[id=%d, Email='%s']",
                id, Email);
    }
    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
