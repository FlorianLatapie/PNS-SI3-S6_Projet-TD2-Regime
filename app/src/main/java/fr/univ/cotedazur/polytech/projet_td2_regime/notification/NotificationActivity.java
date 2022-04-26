package fr.univ.cotedazur.polytech.projet_td2_regime.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class NotificationActivity extends AppCompatActivity {

    private MaterialTimePicker picker;
    private Calendar calendar;
    private Calendar cale;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Meal meal;
    final Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        setContentView(R.layout.activity_notification);
        Button remindMeButton = findViewById(R.id.remindMeButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button horaireButton = findViewById(R.id.horaireButton);

        cale = Calendar.getInstance();
        int minute = cale.get(Calendar.MINUTE);
        int hour = cale.get(Calendar.HOUR);
        updateTime(hour, minute);
        TextView mealName = findViewById(R.id.timerDescriptionNameMeal);
        mealName.setText(meal.getName());

        cancelButton.setOnClickListener(v->{
            cancelAlarm();
        });

        horaireButton.setOnClickListener(v->{
            showTimePicker();
        });

        remindMeButton.setOnClickListener(v -> {
            meal = (Meal) getIntent().getSerializableExtra("Meal");
            System.out.println("Meal " + meal);
            setAlarm();

        });
    }

    private void updateTime(int hour, int minute){
        TextView timeDisplay = findViewById(R.id.timeDisplay);
        if (hour > 12){
            timeDisplay.setText(String.format("%02d",(hour-12)+"")+" : "+String.format("%02d",minute+" PM"));
        }else {
            timeDisplay.setText(hour+" : " + minute + " AM");
        }
    }

    private void cancelAlarm() {
        handler.removeCallbacksAndMessages(null);
        Toast.makeText(this, "Rappel supprimé !", Toast.LENGTH_SHORT).show();
    }


    private void setAlarm( ){
        System.out.println("Meal set Alarm "+meal.getName());
        if(calendar==null) {
            delayNotification(0);
        }else {
            long currentTime = (cale.getTime().getHours()%12)*3600*1000+cale.getTime().getMinutes()*60*1000+cale.getTime().getSeconds()*1000;
            long selectedTime = calendar.getTime().getHours()*3600*1000 + calendar.getTime().getMinutes()*60*1000;
            long delayTime = selectedTime-currentTime;
            if(delayTime<=0)  Toast.makeText(this, "Date sélectionnée invalide !", Toast.LENGTH_SHORT).show();
            else{
                delayNotification(delayTime);
                Toast.makeText(this, "Rappel placé !", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void delayNotification(long timeInMillis){
        final Handler handler = new Handler();
        Context context = this;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Notif notif = new Notif(meal.getName(), "Il est l'heure de cuisiner ! Cliquez pour plus d'informations sur la recette", "", meal);
                notif.sendNotification(context);
                return;

            }
        }, timeInMillis); //first run after 2 minutes
    }


    private void showTimePicker() {
        cale = Calendar.getInstance();
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(cale.get(Calendar.HOUR))
                .setMinute(cale.get(Calendar.MINUTE))
                .setTitleText("Select Alarm Time")
                .build();


        picker.show(getSupportFragmentManager(),"kure");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime(picker.getHour(),picker.getMinute());

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

            }
        });


    }
}
