package com.example.servingwebcontent.Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="customers")
public class CustomerList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Customer> customer = new ArrayList<Customer>();


    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
