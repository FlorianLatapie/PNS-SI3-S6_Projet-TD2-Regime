package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.os.Parcel;

public class Meal {
    private String name;



    private String description;
    private float price;
    private int picture;

    public Meal(String name, String description, float price, int picture) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture =picture;
    }

    protected Meal(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        picture = in.readInt();
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description;}
    public float getPrice() {
        return price;
    }
    public int getPicture(){ return picture; }
}
