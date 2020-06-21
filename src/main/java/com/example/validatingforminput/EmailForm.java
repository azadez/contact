package com.example.validatingforminput;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailForm {


    private String email;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
