package com.example.servingwebcontent;


import com.example.servingwebcontent.Model.*;
import com.example.validatingforminput.EmailForm;
import com.example.validatingforminput.AdresseForm;
import com.example.validatingforminput.PersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("valueSession2")
public class GreetingController implements WebMvcConfigurer {
    String idtemps;

    @Autowired
    CustomerRepository data;
    @Autowired
    AdresseRepository data2;
    @Autowired
    EmailRepository data3;

    @GetMapping("/")
    public String accueil() {
        return "redirect:/greeting";
    }
    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("contacts", data.findAll());
        return "greeting";
    }

    @RequestMapping("/endsession")
    public String endSession(SessionStatus status) {
        status.setComplete();
        return "lastpage";
    }

    @GetMapping("/adressesemails")
    public String showadresses(@RequestParam(name = "id", required = true) String x, Model model) {
        model.addAttribute("contact", data.findById(Long.parseLong(x)));
        return "adressesemails";
    }

    @GetMapping("/add")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/add")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        data.save(new Customer(personForm.getName(), personForm.getlastname()));
        return "redirect:/greeting";
    }

    @GetMapping("/update")
    public String showupdate(PersonForm personForm
            , @RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model, @RequestParam(value = "action", required = false, defaultValue = "custo") String action) {
        if (action.equals("custo")) {
            boolean b;
            try {
                b = data.existsById(Long.parseLong(x));
            } catch (Exception e) {
                return "redirect:/greeting";
            }
            if (b) {
                model.addAttribute("id", x);
                idtemps = x;
                return "update";
            } else return "redirect:/greeting";
        }
        if (action.equals("adresse")) {
            return "redirect:/addadresse?id=" + x;
        }
        if (action.equals("email")) {
            return "redirect:/addemail?id=" + x;
        }
        return "redirect:/greeting";
    }

    @PostMapping("/update")
    public String t(@Valid PersonForm personForm, BindingResult bindingResult, @RequestParam(value = "id", required = false, defaultValue = "-1") String x, @RequestParam(value = "action", required = false, defaultValue = "custo") String action) {

        if (bindingResult.hasErrors()) {
            return "/update?id=" + idtemps;
        }
        Customer custoupdate = data.findById(Long.parseLong(idtemps));
        custoupdate.setFirstName(personForm.getName());
        custoupdate.setLastName(personForm.getlastname());
        data.save(custoupdate);
        return "redirect:/greeting";


    }

    @GetMapping("/addadresse")
    public String addadressecusto(@RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model, AdresseForm adresseForm) {
        List<Adresse> l = data2.findAll();
        l.removeAll(data.findById(Long.parseLong(x)).getAdresses());
        model.addAttribute("adresse", l);
        idtemps = x;
        return "addadresse";
    }

    @PostMapping("/addadresse")
    public String postaddadresseemail(AdresseForm adresseForm) {
        if (adresseForm.getAdresse() != null) {
            Customer c = data.findById(Long.parseLong(idtemps));
            for (String id : adresseForm.getAdresse().split(",")) {
                c.getAdresses().add(data2.findById(Long.parseLong(id)));
            }
            data.save(c);
        }
        return "redirect:/adressesemails?id=" + idtemps;
    }

    @GetMapping("/addemail")
    public String addemailcusto(@RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model, EmailForm emailForm) {
        model.addAttribute("email", data3.findBycustomer(null));
        idtemps = x;
        return "addemail";
    }

    @PostMapping("/addemail")
    public String postemail(Model model, EmailForm emailForm) {
        if (emailForm.getEmail() != null) {
            for (String id : emailForm.getEmail().split(",")) {
                Email e = data3.findById(Long.parseLong(id));
                e.setCustomer(data.findById(Long.parseLong(idtemps)));
                data3.save(e);
            }
        }
        return "redirect:/adressesemails?id=" + idtemps;
    }

    @GetMapping("/delete")
    public String Delete(PersonForm personForm
            , @RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model,
                         @RequestParam(value = "action", required = false, defaultValue = "custo") String action) {
        if (action.equals("custo")) {
            if (data.existsById(Long.parseLong(x))) data.deleteById(Long.parseLong(x));
            return "redirect:/greeting";
        }
        if (action.equals("adresse")) {

            Customer c = data.findById(Long.parseLong(x.split(",")[1]));
            c.getAdresses().remove(data2.findById(Long.parseLong(x.split(",")[0])));
            data.save(c);
            return "redirect:/adressesemails?id=" + x.split(",")[1];
        }
        if (action.equals("email")) {
            Email e = data3.findById(Long.parseLong(x.split(",")[0]));
            e.setCustomer(null);
            data3.save(e);
            return "redirect:/adressesemails?id=" + x.split(",")[1];
        }
        return "redirect:/greeting";
    }

    @GetMapping("/createadresse")
    public String createad() {
        return "createadresse";
    }

    @PostMapping("/createadresse")
    public String postcreatead(@RequestParam(name = "addresses") List<String> addresses) {
        for (String s : addresses) {
            data2.save(new Adresse(s));
        }
        return "redirect:/greeting";
    }

    @GetMapping("/createemail")
    public String createem() {
        return "createemail";
    }

    @PostMapping("/createemail")
    public String postcreateemail(@RequestParam(name = "emails") List<String> emails) {
        for (String s : emails) {
            try {
                data3.save(new Email(s));
            } catch (Exception e) {
            }
        }
        return "redirect:/greeting";
    }

    /*
    a. En entrant http://localhost:8080/xml?action=listContacts, on obtiendrait une réponse au format XML contenant la liste des contacts.
    b. En entrant http://localhost:8080/xml?action=getContact&id=42, on obtiendrait une
    réponse au format XML contenant le contact d’identifiant 42.
    c. En entrant http://localhost:8080/xml?action=delContact&id=42, on supprimerait le
    contact d’identifiant 42
     */
    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    CustomerList apiXML(@RequestParam(value = "id", required = false, defaultValue = "0") String x,
                        @RequestParam(value = "action", required = false, defaultValue = "listContacts") String action,
                        @RequestParam(value = "firstname", required = false, defaultValue = "") String firstname,
                        @RequestParam(value = "lastname", required = false, defaultValue = "") String lastname) {
        CustomerList customer = new CustomerList();
        if (action.equals("listContactsDetail")) {
            for (Customer c : data.findAll()) {
                customer.getCustomer().add(c);
            }
            return customer;
        }
        if (action.equals("listContacts")) {
            for (Customer c : data.findAll()) {
                c.setAdresses(null);
                c.setEmails(null);
                customer.getCustomer().add(c);
            }
            return customer;
        }
        if (action.equals("getContact")) {
            customer.getCustomer().add(data.findById(Long.parseLong(x)));
            return customer;
        }
        if (action.equals("delContact")) {
            if(data.existsById(Long.parseLong(x))){
                Customer c = data.findById(Long.parseLong(x));
                data.deleteById(Long.parseLong(x));
                customer.getCustomer().add(c);
                return customer;
            }return null;
        }
        if (action.equals("updateContact")) {
            if (!firstname.equals("") && !lastname.equals("")) {
                Customer c = data.findById(Long.parseLong(x));
                c.setFirstName(firstname);
                c.setLastName(lastname);
                data.save(c);
            } else if (!firstname.equals("")) {
                Customer c = data.findById(Long.parseLong(x));
                c.setFirstName(firstname);
                data.save(c);
            } else if (!lastname.equals("")) {
                Customer c = data.findById(Long.parseLong(x));
                c.setLastName(lastname);
                data.save(c);
            }
        }
        return null;
    }

    @PostMapping("/inject/customer")
    public ResponseEntity<Customer> injectcusto(@RequestBody Customer custo) {
        try {
            Customer _custo = data.save(new Customer(custo.getFirstName(), custo.getLastName()));
            return new ResponseEntity<>(_custo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/inject/email")
    public ResponseEntity<Email> injectemail(@RequestBody Email email) {
        try {
            try {
                Email _email = data3.save(new Email(email.getEmail()));
                return new ResponseEntity<>(_email, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/inject/adresse")
    public ResponseEntity<Adresse> injectadresse(@RequestBody Adresse adresse) {
        try {
            Adresse _adresse = data2.save(new Adresse(adresse.getAdresse()));
            return new ResponseEntity<>(_adresse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

}




