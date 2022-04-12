package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class User {

    private String firstName;
    private String lastName;
    private Genre gender;
    private int age;
    private int size;
    private String bio;
    private Diet diet;
    private List<Meal> publishedMeals;
    private Double weight;
    private Double weightGoal;
    private ArrayList<Meal> likeMeals;
    private ArrayList<Meal> eatenMeals;
    private Map<LocalDate, Double> weightHistorical;
    private int imageProfile;



    public User(String firstName, String lastName, Genre gender, int age, int size, String bio, Diet diet, Double weight, Double weightGoal, int imageProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.size = size;
        this.bio = bio;
        this.diet = diet;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.imageProfile = imageProfile;
        this.publishedMeals = publishedMeals;
        this.likeMeals = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
        this.publishedMeals = new ArrayList<>();
        this.weightHistorical = new TreeMap<LocalDate, Double>();
        this.weightHistorical.put(LocalDate.now(), Double.valueOf(weight));
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

    public Genre getGender() {
        return gender;
    }

    public void setGender(Genre gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
    public Double getWeight() {
        return weight;
    }

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setWeightGoal(Double weight) {
        this.weightGoal = weight;
    }

    public Map<LocalDate, Double> getWeightHistorical() {
        return weightHistorical;
    }

    public ArrayList<Meal> getLikeMeals() {
        return likeMeals;
    }

    public void setLikeMeals(ArrayList<Meal> likeMeals) {
        this.likeMeals = likeMeals;
    }

    public ArrayList<Meal> getEatenMeals() {
        return eatenMeals;
    }

    public void setEatenMeals(ArrayList<Meal> eatenMeals) {
        this.eatenMeals = eatenMeals;
    }

    public void setWeightHistorical(Map<LocalDate, Double> weightHistorical) {
        this.weightHistorical = weightHistorical;
    }

    public LocalDate getLastWeightDate(){
        Map.Entry<LocalDate,Double> lastEntry = null;
        for (Map.Entry<LocalDate,Double> entry: this.weightHistorical.entrySet()){
            lastEntry = entry;
        }
        return lastEntry.getKey();
    }

    public int getCaloryGoal(){
        if(gender==Genre.HOMME) {
            return (int) ((13.707 * this.weight) + (492.3 * this.size/100) - (6.673 * this.age) + 77.607);
        }
        else{
            return (int) ((9.740*this.weight) + (172.9*(this.size/100)) - (4.737*this.age) + 667.051);
        }
    }
}
