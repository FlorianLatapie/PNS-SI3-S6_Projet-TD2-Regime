package fr.univ.cotedazur.polytech.projet_td2_regime.stats;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdatpter;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class EatenMealsActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaten_meals);

        user = UserManager.getInstance().getCurrentUser();

        List<Meal> eatenMeals = user.getEatenMeals();

        ListView listView = findViewById(R.id.listView);

        MealsAdatpter adapter = new MealsAdatpter(getApplicationContext(), eatenMeals);
        listView.setAdapter(adapter);
    }
}