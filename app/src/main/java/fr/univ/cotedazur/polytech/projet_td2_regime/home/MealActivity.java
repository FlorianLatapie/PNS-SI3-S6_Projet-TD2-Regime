package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        Meal meal = MealsList.get(getIntent().getIntExtra("Meal", 0));
        ((TextView)findViewById( R.id.mealPrice)).setText(meal.getPrice() +"0 €");
        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());
        ((ImageView)findViewById( R.id.imageView)).setImageResource(meal.getPicture());

        ((Button)findViewById(R.id.button)).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("MEAL");
            builder.setMessage("Vous avez cliqué sur : " + meal);
            builder.setNeutralButton("OK", null);
            builder.show();
        });
    }
}