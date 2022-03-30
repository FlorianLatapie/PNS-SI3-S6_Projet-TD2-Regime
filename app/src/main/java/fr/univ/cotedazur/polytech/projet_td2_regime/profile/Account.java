package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.util.ArrayList;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.Comment;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class Account {
    private String name;
    private String myDiet;
    private int weight;
    private ArrayList<Meal> myMeals;
    private ArrayList<Meal> likeMeals;
    private ArrayList<Comment> myComments;

    public Account(String name, String myDiet, int weight) {
        this.name = name;
        this.myDiet= myDiet;
        this.weight = weight;
        this.myMeals= new ArrayList<>();
        this.likeMeals = new ArrayList<>();
        this.myComments = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyDiet() {
        return myDiet;
    }

    public void setMyDiet(String myDiet) {
        this.myDiet = myDiet;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Meal> getMyMeals() {
        return myMeals;
    }

    public void setMyMeals(ArrayList<Meal> myMeals) {
        this.myMeals = myMeals;
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
}
