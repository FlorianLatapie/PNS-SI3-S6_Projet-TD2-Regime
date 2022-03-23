package fr.univ.cotedazur.polytech.projet_td2_regime.home;
import java.util.ArrayList;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealsList{
    private static ArrayList<Meal> mealArrayList = new ArrayList<>();
    private static String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean tristique sollicitudin lacus vitae malesuada. Etiam sapien.\n";
    static {
        mealArrayList.add(new Meal("Fromagio", description,  4, R.drawable.pizza3));
        mealArrayList.add(new Meal("Chorizo",description, 9, R.drawable.pizza2));
        mealArrayList.add(new Meal("Pouleto",description, 5, R.drawable.meal1));
        mealArrayList.add(new Meal("Royalo",description, 7, R.drawable.pizza7));
        mealArrayList.add(new Meal("Calzone",description, 2, R.drawable.pizza4));
        mealArrayList.add(new Meal("Regina",description, 8, R.drawable.pizza5));
        mealArrayList.add(new Meal("indienne",description, 2, R.drawable.pizza6));
        mealArrayList.add(new Meal("Speciale",description, 2, R.drawable.pizza8));
        mealArrayList.add(new Meal("Végetarienne",description, 7, R.drawable.pizza9));
    }

    public static Meal get(int index) {
        return mealArrayList.get(index);
    }

    public static void remove(int index) {
        mealArrayList.remove(index);
    }

    public static int size() {
        return mealArrayList.size();
    }

}