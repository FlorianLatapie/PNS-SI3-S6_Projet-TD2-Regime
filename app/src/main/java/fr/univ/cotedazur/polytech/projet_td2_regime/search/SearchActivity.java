package fr.univ.cotedazur.polytech.projet_td2_regime.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdapter;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.MealApi;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.Util;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Meal> mealsList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        TextView searchedItem = findViewById(R.id.searched_text);
        searchedItem.setText("Vous avez recherché : " + query);

        ListView listView = findViewById(R.id.listViewSearch);
        mealsList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        loadMealsFromApi(query);

        MealsAdapter adapter = new MealsAdapter(getApplicationContext(), mealsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Meal meal = (Meal) o;
                Intent intent = new Intent(getApplicationContext(), MealActivity.class);
                intent.putExtra("Meal", meal);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void loadMealsFromApi(String query) {
        query = Util.replaceSpace(query);

        MealApi mealApi = new MealApi(query, this, listView);
        try {
            mealsList.addAll(mealApi.execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}