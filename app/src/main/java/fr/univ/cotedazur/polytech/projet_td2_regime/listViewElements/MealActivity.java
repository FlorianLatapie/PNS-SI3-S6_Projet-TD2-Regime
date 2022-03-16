package fr.univ.cotedazur.polytech.projet_td2_regime.listViewElements;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;


public class MealActivity extends AppCompatActivity {
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        meal = getIntent().getParcelableExtra(getString(R.string.MEAL));
        ((TextView)findViewById( R.id.mealPrice)).setText(Float.toString(meal.getPrice())+"0 €");
        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());
        ((ImageView)findViewById( R.id.imageView)).setImageResource(meal.getPicture());

        ((Button)findViewById(R.id.button)).setOnClickListener( click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("PIZZA");
            builder.setMessage("Vous avez cliqué sur : " + meal);
            builder.setNeutralButton("OK", null);
            builder.show();
        });
    }
}