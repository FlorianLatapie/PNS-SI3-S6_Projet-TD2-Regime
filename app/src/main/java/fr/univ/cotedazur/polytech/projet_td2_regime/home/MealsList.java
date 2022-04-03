package fr.univ.cotedazur.polytech.projet_td2_regime.home;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealsList {
    private static ArrayList<Meal> mealArrayList = new ArrayList<>();

    static {
        mealArrayList.add(new Meal("Fromage", R.drawable.pizza3));
        mealArrayList.add(new Meal("Chorizo", R.drawable.pizza2));
        mealArrayList.add(new Meal("Poulet", R.drawable.meal1));
        mealArrayList.add(new Meal("Royale", R.drawable.pizza7));
        mealArrayList.add(new Meal("Calzone", R.drawable.pizza4));
        mealArrayList.add(new Meal("Regina", R.drawable.pizza5));
        mealArrayList.add(new Meal("indienne", R.drawable.pizza6));
        mealArrayList.add(new Meal("Speciale", R.drawable.pizza8));
        mealArrayList.add(new Meal("Végetarienne", R.drawable.pizza9));
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