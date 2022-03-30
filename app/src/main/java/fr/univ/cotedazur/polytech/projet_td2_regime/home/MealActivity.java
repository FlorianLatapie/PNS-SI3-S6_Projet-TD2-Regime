package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class MealActivity extends AppCompatActivity {
    TextView mealNameFirebase;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        Meal meal = MealsList.get(getIntent().getIntExtra("Meal", 0));
        ((TextView)findViewById( R.id.mealPrice)).setText(meal.getPrice() +"0 €");
        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());
        ((ImageView)findViewById( R.id.imageView)).setImageResource(meal.getPicture());
        mealNameFirebase = findViewById( R.id.mealNameFirebase);

        DocumentReference documentReference = db.collection("recipes").document("recipe");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                mealNameFirebase.setText(value.getString("name"));
            }
        });

        ((Button)findViewById(R.id.button)).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("MEAL");
            builder.setMessage("Vous avez cliqué sur : " + meal);
            builder.setNeutralButton("OK", null);
            builder.show();
        });
    }
}