package com.example.servingwebcontent;

import com.example.servingwebcontent.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class ServingWebContentApplication implements CommandLineRunner {
    Customer HOMER = new Customer("Homer","simpson");
    Customer LISA = new Customer("Lisa","simpson");
    Customer BARTH = new Customer("Barth","simpson");
    Customer MARGE = new Customer("Marge","simpson");
    Customer MAGGY = new Customer("Maggy","simpson");
    Adresse Ad1 = new Adresse("15 rue de champagne", "67000", "Reims");
    Adresse Ad2 = new Adresse("1 avenue du général leclerc" ,"54500" ,"vandoeuvre");
    Adresse Ad3 = new Adresse("66 rue jean jaurès", "54000", "nancy");
    Adresse Ad4 = new Adresse("Place des champs élyssé","93000","Paris");
    Adresse Ad5 = new Adresse("Place Stanylas","93000","Paris");
    Email Em1 = new Email("hugo.malglaive@gmail.com");
    Email Em2= new Email("hugo.robert@gmail.com");
    Email Em3 = new Email("raphael.carlier@lecnam.net");
    Email Em4 = new Email("emmanuel.macron@gouv.fr");
    Email Em5 = new Email("huguel.malglaive@gmail.com");
    List<Customer> GENS = Arrays.asList(HOMER,LISA,BARTH,MAGGY,MARGE);
    List<Adresse> ADRESSE = Arrays.asList(Ad1,Ad2,Ad3,Ad4,Ad5);
    List<Email> EMAIL = Arrays.asList(Em1,Em2,Em3,Em4,Em5);

    @Autowired
    CustomerRepository data;
    @Autowired
    AdresseRepository data2;
    @Autowired
    EmailRepository data3;
    @Autowired
    UserRepository data4;




    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        data.saveAll(GENS);
        data2.saveAll(ADRESSE);
        data3.saveAll(EMAIL);
        Customer test = data.findById(1);
        test.getAdresses().add(data2.findById(6L));
        test.getAdresses().add(data2.findById(7L));
        data.save(test);
        test = data.findById(2L);
        test.getAdresses().add(data2.findById(8L));
        test.getAdresses().add(data2.findById(9L));
        data.save(test);

        Email e = data3.findById(11L);
        e.setCustomer(data.findById(1L));
        data3.save(e);
        e = data3.findById(12L);
        e.setCustomer(data.findById(1L));
        data3.save(e);
        e = data3.findById(14L);
        e.setCustomer(data.findById(2L));
        data3.save(e);
        e = data3.findById(13L);
        e.setCustomer(data.findById(2L));
        data3.save(e);




    }




}