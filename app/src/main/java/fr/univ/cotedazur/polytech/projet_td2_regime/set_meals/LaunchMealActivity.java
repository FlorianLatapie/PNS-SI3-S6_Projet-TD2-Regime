package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.HomeFragment;

public class LaunchMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_meal);

        Button myButton = findViewById(R.id.mybutton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(LaunchMealActivity.this);
                HomeFragment viewHome = new HomeFragment();
                model.addObserver(viewHome);

                Controller controller = new Controller(model, viewHome);
            }
        });
    }
}