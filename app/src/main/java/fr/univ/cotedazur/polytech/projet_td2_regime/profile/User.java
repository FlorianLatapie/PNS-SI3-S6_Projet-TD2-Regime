package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.util.ArrayList;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.Comment;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class User {

    private String firstName;
    private String lastName;
    private String bio;
    private Diet diet;
    private List<Meal> publishedMeals;
    private int weight;
    private ArrayList<Meal> likeMeals;
    private ArrayList<Comment> myComments;
    private ArrayList<Meal> eatenMeals;



    public User(String firstName, String lastName, String bio, Diet diet, int weight, List<Meal> publishedMeals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.diet = diet;
        this.publishedMeals = publishedMeals;
        this.likeMeals = new ArrayList<>();
        this.myComments = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Meal> getLikeMeals() {
        return likeMeals;
    }

    public void setLikeMeals(ArrayList<Meal> likeMeals) {
        this.likeMeals = likeMeals;
    }

    public ArrayList<Comment> getMyComments() {
        return myComments;
    }

    public void setMyComments(ArrayList<Comment> myComments) {
        this.myComments = myComments;
    }
    public ArrayList<Meal> getEatenMeals() {
        return eatenMeals;
    }

    public void setEatenMeals(ArrayList<Meal> eatenMeals) {
        this.eatenMeals = eatenMeals;
    }
}
