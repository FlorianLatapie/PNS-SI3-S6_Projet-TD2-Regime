package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class HomeFragment extends Fragment implements IListner {
    private IListner listener;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.meal_fragment, container, false);

        MealsAdatpter adapter = new MealsAdatpter(getActivity().getApplicationContext());

        //Récupération du composant ListView
        ListView list = view.findViewById(R.id.listView);

        //Initialisation de la liste avec les données

        list.setAdapter(adapter);

        adapter.addListener(this);

        //return inflater.inflate(R.layout.meal_fragment, container, false);
        return view;
    }

    @Override
    public void onClickMeal(int position) {
        Intent intent = new Intent( getActivity().getApplicationContext(), MealActivity.class);
        intent.putExtra("Meal", position);
        startActivity(intent);
    }
}
