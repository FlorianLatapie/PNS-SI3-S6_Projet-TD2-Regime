package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.os.Parcel;

public class Meal {
    private String name;
    private float price;
    private int picture;
    private int preparationTime;
    private int nbOfPeople;
    private String ingredients;
    private String preparation;
    private int kcal;
    private int eatIt;
    private int likes;
    private int comments;
    private String author;


    public Meal(String name, float price, int picture) {
        this.name = name;
        this.price = price;
        this.picture =picture;
        this.ingredients = "Lorem ipsum dolor sit amet. Nam alias dignissimos a incidunt consequatur qui doloremque deserunt aut voluptas quis ut illo";
        this.preparation = "Lorem ipsum dolor sit amet. Nam alias dignissimos a incidunt consequatur qui doloremque deserunt aut voluptas quis ut illo";
        this.kcal = 321;
        this.likes= 32;
        this.comments = 153;
        this.author = "Pierre";

    }

    protected Meal(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        picture = in.readInt();
    }

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getPicture(){ return picture;}
    public String getIngredients(){ return ingredients;}
    public String getPreparation(){ return preparation;}
    public int getKcal(){ return kcal;}
    public int getEatIt(){ return eatIt;}
    public int getLikes() { return likes;}
    public int getComments() { return comments;}
    public String getAuthor() { return author;}
    public int getPreparationTime() {
        return preparationTime;
    }

    public int getNbOfPeople() {
        return nbOfPeople;
    }

    public void setEatIt(boolean bool){ this.eatIt++;}
}
