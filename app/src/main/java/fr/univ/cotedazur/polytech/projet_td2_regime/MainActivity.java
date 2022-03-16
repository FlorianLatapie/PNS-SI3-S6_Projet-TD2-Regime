package fr.univ.cotedazur.polytech.projet_td2_regime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements.MealsAdapter;
import fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements.MealsList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MealsList meals = new MealsList();
        MealsAdapter adapter = new MealsAdapter(getApplicationContext(), meals);

        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}