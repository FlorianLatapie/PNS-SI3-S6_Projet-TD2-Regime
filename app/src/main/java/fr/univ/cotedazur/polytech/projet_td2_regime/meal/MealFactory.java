package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

import android.graphics.Bitmap;
import android.os.Parcel;

public class MealFactory {
    public static final int MEAL_LUNCH = 1;
    public static final int MEAL_STARTER = 2;
    public static final int MEAL_DESSERT = 3;

    public static Meal build(int type) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch();
            case MEAL_STARTER:
                return new MealStarter();
            case MEAL_DESSERT:
                return new MealDessert();
            default:
                throw new Throwable("not made");
        }
    }

    public static Meal build(int type, String name, int picture) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch(name, picture);
            case MEAL_STARTER:
                return new MealStarter(name, picture);
            case MEAL_DESSERT:
                return new MealDessert(name, picture);
            default:
                throw new Throwable("not made");
        }
    }

    public static Meal build(int type, String name, Bitmap picture, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch(name, picture, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            case MEAL_STARTER:
                return new MealStarter(name, picture, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            case MEAL_DESSERT:
                return new MealDessert(name, picture, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            default:
                throw new Throwable("not made");
        }
    }

    public static Meal build(int type, String name, String imageLink, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch(name, imageLink, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            case MEAL_STARTER:
                return new MealStarter(name, imageLink, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            case MEAL_DESSERT:
                return new MealDessert(name, imageLink, preparationTime, nbOfPeople, ingredients, preparation, kcal, author);
            default:
                throw new Throwable("not made");
        }
    }

    public static Meal build(int type, Parcel in) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch(in);
            case MEAL_STARTER:
                return new MealStarter(in);
            case MEAL_DESSERT:
                return new MealDessert(in);
            default:
                throw new Throwable("not made");
        }
    }
}
