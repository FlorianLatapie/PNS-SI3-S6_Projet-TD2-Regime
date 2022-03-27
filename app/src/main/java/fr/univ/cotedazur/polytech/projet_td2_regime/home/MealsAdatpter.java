package fr.univ.cotedazur.polytech.projet_td2_regime.home;

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

public class MealsAdatpter extends BaseAdapter {
    private IListner listener;

    //Le contexte dans lequel est présent notre adapter
    private Context context;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater inflater;

    public MealsAdatpter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return MealsList.size();
    }


    @Override
    public Object getItem(int position) {
        return MealsList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? inflater.inflate(R.layout.meal_layout, parent, false) : convertView);


        //(2) : Récupération des TextView de notre layout
        TextView tvName = layoutItem.findViewById(R.id.mealName);
        TextView tvPrice = layoutItem.findViewById(R.id.mealPrice);
        ImageView mealPicture = layoutItem.findViewById(R.id.mealPicture);

        //(3) : Renseignement des valeurs
        tvName.setText(MealsList.get(position).getName());
        tvPrice.setText(MealsList.get(position).getPrice() + "0 €");  //TODO change uggly text format
        mealPicture.setImageResource(MealsList.get(position).getPicture());

        //(4) Changement de la couleur du fond de notre item
        tvPrice.setTextColor(MealsList.get(position).getPrice() >= 5 ? Color.RED : Color.BLACK);

        layoutItem.setOnClickListener(click -> {
            listener.onClickMeal(position);
        });
        //On retourne l'item créé.
        return layoutItem;
    }

    public void addListener(IListner listener) {
        this.listener = listener;
    }
}