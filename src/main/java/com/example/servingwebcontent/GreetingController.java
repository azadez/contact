package com.example.servingwebcontent;


import com.example.servingwebcontent.Model.*;
import com.example.validatingforminput.EmailForm;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
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

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("contacts", data.findAll());
        return "greeting";
    }


//    @ModelAttribute("someList")
//    public List<String> getSomeList(){
//        List<String> str = new ArrayList<>();
//        for (Customer customer : data.findAll()) {
//            str.add(customer.toString());
//            for (Adresse ad : customer.getAdresses()){
//                str.add(ad.toString());
//            }
//        }
//        return str;
//    }


    @GetMapping("/testParam/{id}")
    public String testParam(
            @PathVariable int id,
            Model model) {
        model.addAttribute("id", id);
        return "param";
    }

    @RequestMapping("/endsession")
    public String endSession(SessionStatus status) {
        status.setComplete();
        return "lastpage";
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @GetMapping("/results")
    public String result(@RequestParam(name = "name", required = false, defaultValue = "nobody") String person, Model model) {
        //String str =data.findById(1L).getFirstName();
        model.addAttribute("person", "testperson");
        return "results";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "form";
        }
        data.save(new Customer(personForm.getName(), personForm.getlastname()));


        return "redirect:/greeting";
    }

    @GetMapping("/Liste")
    public String Liste() {

        return "Liste";
    }

    //rajouter la prise en compte d'un id pas dans la liste
    @GetMapping("/update")
    public String showupdate(PersonForm personForm
            , @RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model) {
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

    @PostMapping("/update")
    public String t(@Valid PersonForm personForm, BindingResult bindingResult, @RequestParam(value = "id", required = false, defaultValue = "-1") String x) {


        if (bindingResult.hasErrors()) {
            return "/update?id=" + idtemps;
        }
        Customer custoupdate = data.findById(Long.parseLong(idtemps));
        custoupdate.setFirstName(personForm.getName());
        custoupdate.setLastName(personForm.getlastname());
        data.save(custoupdate);


        return "redirect:/greeting";
    }

    //localhost:8080/delete?id=5
    @GetMapping("/delete")
    public String Delete(PersonForm personForm
            , @RequestParam(value = "id", required = false, defaultValue = "-1") String x, Model model) {
        try {
            data.deleteById(Long.parseLong(x));
        } catch (Exception e) {
            return "redirect:/greeting";
        }
        return "redirect:/greeting";
    }

    @GetMapping("/test")
    public String test() {
        Email e = data3.findById(11L);
        e.setCustomer(null);
        data3.save(e);
        /*Customer c = data.findById(1L);
        c.getEmails().remove(e);
        data.save(c);
        List<Email> t = data3.findAll();
        t.removeAll(data.findById(1L).getEmails());*/
        return "greeting";
    }

    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    CustomerList apiXML(@RequestParam(value = "id", required = false, defaultValue = "0") String x,@RequestParam(value ="action",required = false,defaultValue = "GetCusto")String action) {
        CustomerList customer = new CustomerList();
        if (x.equals("0")&&action.equals("GetCusto")) {

            for (Customer c : data.findAll()) {
                customer.getCustomer().add(c);
            }
            return customer;
        } else if(!x.equals("0")&&action.equals("GetCusto")){
            customer.getCustomer().add(data.findById(Long.parseLong(x)));
            return customer;
        }if (action.equals("Delcusto")&&!x.equals("0")){
            if(data.existsById(Long.parseLong(x))){
                data.deleteById(Long.parseLong(x));
                return null;
            }return null;
        }
        return null;
    }

    @PostMapping("/inject/customer")
    public ResponseEntity<Customer> createcusto(@RequestBody Customer custo) {
        try {
            Customer _custo = data.save(new Customer(custo.getFirstName(), custo.getLastName()));
            return new ResponseEntity<>(_custo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/inject/email")
    public ResponseEntity<Email> createemail(@RequestBody Email email) {
        try {
            Email _email = data3.save(new Email(email.getEmail()));
            return new ResponseEntity<>(_email, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @GetMapping("/testliste")
    public String attributionemailadresse(Model model, EmailForm e) {
        model.addAttribute("email", data3.findAll());
        return "testliste";
    }

    @PostMapping("/testliste")
    public String testpost(EmailForm e, Model model) {
        //String[] t = model.getAttribute("email");
        return "greeting";
    }

    @GetMapping( "/testt")
    public String teste(){

        return "testt";
    }

    @PostMapping("/testt")
    public String recup(@RequestParam(name="addresses") List<String> addresses){
        return "redirect:/greeting";
    }

}




