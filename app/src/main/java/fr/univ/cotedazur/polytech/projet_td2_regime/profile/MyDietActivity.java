package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MyDietActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diet);

        Spinner regime = findViewById(R.id.specificity_spinner);

        regime.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        HealthMonitoring.getNames()
                )
        );
        regime.setOnItemSelectedListener(new SpinnerDietListener(this));

        Spinner suiviDeSante = findViewById(R.id.health_spinner);

        suiviDeSante.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        Diet.getNames()
                )
        );
    }
}
