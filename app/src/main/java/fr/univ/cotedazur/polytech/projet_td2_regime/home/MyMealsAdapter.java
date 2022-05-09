package fr.univ.cotedazur.polytech.projet_td2_regime.home;

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
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.DownloadImageTask;

public class MyMealsAdapter extends BaseAdapter {
    private IListner listener;

    //Le contexte dans lequel est présent notre adapter
    private Context context;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater inflater;

    private List<Meal> mealsList;

    public MyMealsAdapter(Context context, List<Meal> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mealsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mealsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? inflater.inflate(R.layout.my_meals_layout, parent, false) : convertView);

        //(2) : Récupération des TextView de notre layout
        TextView tvName = layoutItem.findViewById(R.id.myMealName);
        ImageView mealPicture = layoutItem.findViewById(R.id.myMealPicture);

        //(3) : Renseignement des valeurs
        Meal meal = this.mealsList.get(position);
        tvName.setText(meal.getName());

        if (meal.getImageLink() == null) {
            mealPicture.setImageResource(meal.getPicture());
        } else {
            new DownloadImageTask(mealPicture, meal.getImageLink()).execute();
        }

        //On retourne l'item créé.
        return layoutItem;
    }

    public void addListener(IListner listener) {
        this.listener = listener;
    }
}
