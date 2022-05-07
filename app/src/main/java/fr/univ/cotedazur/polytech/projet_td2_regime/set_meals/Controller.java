package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.HomeFragment;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.IListner;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.DownloadImageTask;

public class Controller {

    private IListner listener;

    private Model model;
    private HomeFragment view;

    public Controller(Model model, HomeFragment view) {
        this.model = model;
        this.view = view;
    }

    public List<Meal> getMeals(){
        return this.model.getMealsList();
    }


    public void viewItemUpdated(int position){
        this.model.addItemToList(position);
    }

    public void addListener(IListner listener) {
        this.listener = listener;
    }
}
