package fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealsAdapter extends BaseAdapter {

    private IListner listener;
    private MealsList meals;

    //Le contexte dans lequel est présent notre adapter
    private Context context;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater inflater;


    public MealsAdapter(Context context, MealsList meals) {
        this.context = context;
        this.meals = meals;
        inflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return meals.size();
    }
    public Object getItem(int position) {
        return meals.get(position);
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? inflater.inflate(R.layout.meal_layout, parent, false) : convertView);


        //(2) : Récupération des TextView de notre layout
        TextView tvName = layoutItem.findViewById(R.id.mealName);
        TextView tvPrice = layoutItem.findViewById(R.id.mealPrice);
        ImageView pizzaPicture = layoutItem.findViewById(R.id.mealPicture);

        //(3) : Renseignement des valeurs
        tvName.setText(meals.get(position).getName());
        tvPrice.setText(Float.toString(meals.get(position).getPrice())+"0 €");  //TODO change uggly text format
        pizzaPicture.setImageResource(meals.get(position).getPicture());

        //(4) Changement de la couleur du fond de notre item
        tvPrice.setTextColor( meals.get(position).getPrice() >= 5 ? Color.RED : Color.BLACK);

        layoutItem.setOnClickListener( click -> {
            listener.onClickMeal(meals.get(position));
        });
        //On retourne l'item créé.
        return layoutItem;
    }

    //abonnement pour click sur le nom...
    public void addListener(IListner listener) {
        this.listener = listener;
    }


}
