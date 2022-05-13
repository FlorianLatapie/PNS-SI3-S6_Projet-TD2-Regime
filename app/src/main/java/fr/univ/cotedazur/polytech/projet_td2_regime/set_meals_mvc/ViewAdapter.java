package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals_mvc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.DownloadImageTask;

public class ViewAdapter extends BaseAdapter {
    private final String TAG = "kure " + getClass().getSimpleName();
    private LayoutInflater inflater;
    private ModelMeal model;
    private ViewMeal viewMeal;


    public <T extends ViewGroup> ViewAdapter(Context context, ViewMeal viewMeal) {
        inflater = LayoutInflater.from(context);
        this.viewMeal = viewMeal;
    }

    public int getCount() {
        return model.size();
    }

    public Object getItem(int position) {
        return model.get(position);
    }

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
        ImageView mealPicture = layoutItem.findViewById(R.id.mealPicture);

        //(3) : Renseignement des valeurs
        tvName.setText(model.get(position)+"");

        //On retourne l'item créé.
        return layoutItem;
    }


    public void refresh(ModelMeal model) {
        updateModel(model);
        Log.d(TAG, "listView refreshed with ==> " + model);
        notifyDataSetChanged();
    }

    public void updateModel(ModelMeal model) {
        this.model = model;
    }
}
