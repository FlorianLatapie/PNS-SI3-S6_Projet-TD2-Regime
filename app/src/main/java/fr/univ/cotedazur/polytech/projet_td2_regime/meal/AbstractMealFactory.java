package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

import android.graphics.Bitmap;
import android.os.Parcel;

public abstract class AbstractMealFactory {
    public static final int MEAL_LUNCH = 1;
    public static final int MEAL_STARTER = 2;
    public static final int MEAL_DESSERT = 3;

    public abstract Meal build(int type) throws Throwable;

    public abstract Meal build(int type, String name, int picture) throws Throwable;

    public abstract Meal build(int type, String name, Bitmap picture, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) throws Throwable;

    public abstract Meal build(int type, String name, String imageLink, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) throws Throwable;
    public abstract Meal build(int type, Parcel in) throws Throwable ;

    //buildMeal(params);
    //buildImage(params);

}
