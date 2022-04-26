package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.notification.Notif;

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
    private Map<LocalDate, Double> weightHistory;
    private int imageProfile;
    private List<Notif> notificationList;

    public User() {
    }

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
        this.likeMeals = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
        this.publishedMeals = new ArrayList<>();
        this.weightHistory = new TreeMap<>();
        this.weightHistory.put(LocalDate.now(), weight);
        this.loadNotifications();
    }

    private void loadNotifications() {
        List<Notif> notifications = new ArrayList<>();
        this.notificationList = notifications;
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

    public void setPublishedMeals(List<Meal> publishedMeals) {
        this.publishedMeals = publishedMeals;
    }

    public int getImageProfile() {
        return this.imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Double weight) {
        this.weightGoal = weight;
    }

    public Map<LocalDate, Double> getWeightHistory() {
        return weightHistory;
    }

    public void setWeightHistory(Map<LocalDate, Double> weightHistory) {
        this.weightHistory = weightHistory;
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

    public LocalDate getLastWeightDate() {
        Map.Entry<LocalDate, Double> lastEntry = null;
        for (Map.Entry<LocalDate, Double> entry : this.weightHistory.entrySet()) {
            lastEntry = entry;
        }
        return lastEntry.getKey();
    }

    public int getCalorieGoal() {
        if (gender.equals(Genre.HOMME)) {
            return (int) ((13.707 * this.weight) + (492.3 * this.size / 100) - (6.673 * this.age) + 77.607);
        } else {
            return (int) ((9.740 * this.weight) + (172.9 * (this.size / 100)) - (4.737 * this.age) + 667.051);
        }
    }

    public List<Notif> getNotifications() {
        return this.notificationList;
    }

    public void addNotification(Notif notif) {
        this.notificationList.add(notif);
    }
}
