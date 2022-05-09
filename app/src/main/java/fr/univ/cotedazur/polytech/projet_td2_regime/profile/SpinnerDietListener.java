package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class SpinnerDietListener extends Activity implements AdapterView.OnItemSelectedListener {
    private MyDietActivity myDietActivity;

    public SpinnerDietListener(MyDietActivity myDietActivity) {
        this.myDietActivity = myDietActivity;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
        TextView dietName = myDietActivity.findViewById(R.id.regimeNom);
        dietName.setText(parent.getItemAtPosition(pos).toString());

        TextView dietDescription = myDietActivity.findViewById(R.id.regimeDescription);
        dietDescription.setText(HealthMonitoring.getHealthMonitoring(parent.getItemAtPosition(pos).toString()).getDescription());

        // todo : set the diet in the user and database
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : nothing selected",
                Toast.LENGTH_SHORT).show();

    }
}