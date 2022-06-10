package com.example.pet_malo;

public class petlist_model {
    String cust_email, name, birthday, gender, breed, species, date, againts, manufacturer, veterinarian;

    public petlist_model() {
    }

    public petlist_model(String cust_email, String name, String birthday, String gender, String breed, String species, String date, String againts, String manufacturer, String veterinarian) {
        this.cust_email = cust_email;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.breed = breed;
        this.species = species;
        this.date = date;
        this.againts = againts;
        this.manufacturer = manufacturer;
        this.veterinarian = veterinarian;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgaints() {
        return againts;
    }

    public void setAgaints(String againts) {
        this.againts = againts;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }
}
