package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

import java.util.ArrayList;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealsList {
    private static ArrayList<Meal> mealArrayList = new ArrayList<>();

    static {
        try {
            mealArrayList.add(MealFactory.build(1, "Fromage", R.drawable.pizza3));
            mealArrayList.add(MealFactory.build(1, "Chorizo", R.drawable.pizza2));
            mealArrayList.add(MealFactory.build(1, "Poulet", R.drawable.meal1));
            mealArrayList.add(MealFactory.build(1, "Royale", R.drawable.pizza7));
            mealArrayList.add(MealFactory.build(1, "Calzone", R.drawable.pizza4));
            mealArrayList.add(MealFactory.build(1, "Regina", R.drawable.pizza5));
            mealArrayList.add(MealFactory.build(1, "indienne", R.drawable.pizza6));
            mealArrayList.add(MealFactory.build(1, "Speciale", R.drawable.pizza8));
            mealArrayList.add(MealFactory.build(1, "Végetarienne", R.drawable.pizza9));
        } catch (Throwable e) {
        }
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