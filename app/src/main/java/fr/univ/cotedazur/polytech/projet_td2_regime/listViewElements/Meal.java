package fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements;

import android.os.Parcel;
import android.os.Parcelable;

class Meal implements Parcelable {
    private String name;
    private float price;
    private int picture;

    public Meal(String name, float price, int picture) {
        this.name = name;
        this.price = price;
        this.picture =picture;
    }

    protected Meal(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        picture = in.readInt();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getPicture(){ return picture; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeInt(picture);
    }
}