package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.IListner;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class LikeAdapter extends BaseAdapter {
    private Context context;
    private List<Meal> likeMeals;
    private LayoutInflater inflater;
    private IListner listener;
    private User user;

    public LikeAdapter(Context context){
        this.context= context;
        this.user = UserManager.getInstance().getCurrentUser();
        this.likeMeals = user.getLikeMeals();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return likeMeals.size();
    }

    @Override
    public Meal getItem(int position) {
        return likeMeals.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.likemeal_layout, null);

        Meal currentMeal = getItem(i);
        System.out.println(currentMeal+"\n meals: "+ user.getLikeMeals());

        ((TextView)view.findViewById( R.id.mealLikeName)).setText(currentMeal.getName());

        ((ImageView)view.findViewById( R.id.mealLikePicture)).setImageResource(currentMeal.getPicture());

        ((TextView)view.findViewById( R.id.mealLikePrice)).setText(""+currentMeal.getLikes());


        return view;
    }


    public void addListener(IListner listener) {
        this.listener = listener;
    }
}
