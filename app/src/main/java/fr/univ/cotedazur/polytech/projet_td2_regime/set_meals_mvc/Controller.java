package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals_mvc;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.HomeFragment;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.IListner;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;

public class Controller implements IViewClick {
    private final String TAG = "kure " + getClass().getSimpleName();
    private IListner listener;
    private ViewMeal view;
    private ModelMeal model;

    public Controller(ModelMeal model, ViewMeal view) {
        this.model = model;
        this.view = view;
    }

    public List<Meal> getMeals(){
        return this.model.getMealsList();
    }

    @Override
    public void onClickItem(int position) {
        Log.d(TAG, "item clicked = " + position );
        if (model.size()>0) {
            model.remove(position);
            if (model.size() == 0) {
                Log.d(TAG, "empty meals");
            }
        }
    }
}
