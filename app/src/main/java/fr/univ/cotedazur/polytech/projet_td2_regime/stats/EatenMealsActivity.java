package fr.univ.cotedazur.polytech.projet_td2_regime.stats;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdapter;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class EatenMealsActivity extends AppCompatActivity {

    User user;
    private EditText editText;
    private DatePicker datePicker;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaten_meals);

        user = UserManager.getInstance().getCurrentUser();

        calendar = Calendar.getInstance();
        date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        this.editText = findViewById(R.id.spinner);

        editText.setText(dateFormat.format(calendar.getTime()));
        editText.setOnClickListener(click -> showCalendar());

        ((TextView)findViewById(R.id.poids)).setText("Poids de la semaine : " + user.getWeight());

        List<Meal> eatenMeals = user.getEatenMeals();

        ListView listView = findViewById(R.id.listView);

        MealsAdapter adapter = new MealsAdapter(getApplicationContext(), eatenMeals);
        listView.setAdapter(adapter);
    }

    private void updateLabel() {
        editText.setText(dateFormat.format(calendar.getTime()));
    }

    private void showCalendar() {
        new DatePickerDialog(EatenMealsActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}