package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class User {

    private String firstName;
    private String lastName;
    private String bio;
    private Diet diet;
    private List<Meal> publishedMeals;

    public User(String firstName, String lastName, String bio, Diet diet, List<Meal> publishedMeals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.diet = diet;
        this.publishedMeals = publishedMeals;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public List<Meal> getPublishedMeals() {
        return publishedMeals;
    }

    public void setPublishedMeals(List<Meal> publishedMeals) {
        this.publishedMeals = publishedMeals;
    }
}