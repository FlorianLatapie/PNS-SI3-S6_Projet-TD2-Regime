package fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements;

import java.util.ArrayList;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements.Meal;

public class MealsList extends ArrayList<Meal> {
    public MealsList(){
        add(new Meal("Fromage", 4, R.drawable.pizza3));
        add(new Meal("Chorizo", 9, R.drawable.pizza2));
        add(new Meal("Poulet", 5, R.drawable.pizza1));
        add(new Meal("Royale", 7, R.drawable.pizza7));
        add(new Meal("Calzone", 2, R.drawable.pizza4));
        add(new Meal("Regina", 8, R.drawable.pizza5));
        add(new Meal("indienne", 2, R.drawable.pizza6));
        add(new Meal("Speciale", 2, R.drawable.pizza8));
        add(new Meal("Végetarienne",7, R.drawable.pizza9));
    }

}