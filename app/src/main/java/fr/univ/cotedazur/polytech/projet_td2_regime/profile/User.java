package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.Comment;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String bio;
    private Diet diet;
    private List<Meal> publishedMeals;
    private int weight;
    private List<Meal> likeMeals;
    private List<Meal> eatenMeals;
    private int imageProfile;

    public User(){
    }

    public User(String firstName, String lastName, String bio, Diet diet, int weight, int imageProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.diet = diet;
        this.weight = weight;
        this.imageProfile = imageProfile;
        this.likeMeals = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
        this.publishedMeals = new ArrayList<>();
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

    public void setPublishedMeals(List<Meal> publishedMeals) { this.publishedMeals = publishedMeals; }

    public void setImageProfile(int imageProfile){ this.imageProfile = imageProfile; }

    public int getImageProfile(){
        return this.imageProfile;
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Meal> getLikeMeals() {
        return likeMeals;
    }

    public void setLikeMeals(List<Meal> likeMeals) {
        this.likeMeals = likeMeals;
    }

    public List<Meal> getEatenMeals() {
        return eatenMeals;
    }

    public void setEatenMeals(List<Meal> eatenMeals) {
        this.eatenMeals = eatenMeals;
    }
}
