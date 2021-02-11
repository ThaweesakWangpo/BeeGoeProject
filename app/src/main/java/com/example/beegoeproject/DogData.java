package com.example.beegoeproject;

public class DogData {
    private  String dogName, gender, birthday, breed, weight;

    public DogData () {

    }

    public DogData(String dogName, String gender, String birthday, String breed, String weight) {
        this.dogName = dogName;
        this.gender = gender;
        this.birthday = birthday;
        this.breed = breed;
        this.weight = weight;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
