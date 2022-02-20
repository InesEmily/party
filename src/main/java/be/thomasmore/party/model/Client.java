package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.*;
@Entity
public class Client {
    @Id
    private Integer id;
    private String name;
    private String birthdate;
    private String gender;
    private String startdate;

    public Client() {
    }

    public Client(String name, String birthdate, String gender, String startdate) {
        this.name = name;
        this.birthdate = birthdate;
        if ( this.gender.equals("M")||this.gender.equals("F")){
            this.gender = gender;
        }else{
            this.gender = "U";
        }
        this.startdate = startdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
}

