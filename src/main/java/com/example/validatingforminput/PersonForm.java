package com.example.validatingforminput;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {
    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=2, max=30)
    private String lastname;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", lastname: " + this.lastname + ")";
    }
}
