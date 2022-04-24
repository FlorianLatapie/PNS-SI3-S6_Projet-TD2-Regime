package fr.univ.cotedazur.polytech.projet_td2_regime.notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class NotificationActivity extends AppCompatActivity {

    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Meal meal;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        setContentView(R.layout.activity_notification);
        Button remindMeButton = findViewById(R.id.remindMeButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button horaireButton = findViewById(R.id.horaireButton);
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
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
        Intent intent = new Intent(this,ReminderBroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Rappel supprimé !", Toast.LENGTH_SHORT).show();
    }


    private void setAlarm( ){
        System.out.println("Meal set Alarm "+meal.getName());
        ReminderBroadcast reminderBroadcast = new ReminderBroadcast();
        if(calendar==null) {
            delayNotification(0);
        }else {
            Calendar cal = Calendar.getInstance();

            Calendar cale = Calendar.getInstance();
            cale.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, cale.HOUR, cale.MINUTE);
            delayNotification(cale.getTimeInMillis() - calendar.getTimeInMillis());
        }

        Toast.makeText(this, "Rappel placé !", Toast.LENGTH_SHORT).show();
    }

    private void delayNotification(long timeInMillis){
        final Handler handler = new Handler();
        ReminderBroadcast reminderBroadcast = new ReminderBroadcast();
        Context context = this;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reminderBroadcast.sendNotification(context,meal);
                return;

            }
        }, timeInMillis); //first run after 2 minutes
    }


    private void showTimePicker() {
        Calendar cal = Calendar.getInstance();
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(cal.get(Calendar.HOUR))
                .setMinute(cal.get(Calendar.MINUTE))
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
