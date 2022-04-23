package fr.univ.cotedazur.polytech.projet_td2_regime.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class NotificationActivity extends AppCompatActivity {

    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);
        createNotificationChannel();
        Button remindMeButton = findViewById(R.id.remindMeButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button horaireButton = findViewById(R.id.horaireButton);

        cancelButton.setOnClickListener(v->{
            cancelAlarm();
        });

        horaireButton.setOnClickListener(v->{
            showTimePicker();
        });

        remindMeButton.setOnClickListener(v -> {
            setAlarm();

        });
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this,ReminderBroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarme supprimée !", Toast.LENGTH_SHORT).show();
    }


    private void setAlarm(){
        Intent intent = new Intent(NotificationActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0 , intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Rappel placé !", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "notifyMealChannel";
            String description = "Channel for meal reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyMeal", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showTimePicker() {

        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(),"foxandroid");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView timeDisplay = findViewById(R.id.timeDisplay);
                if (picker.getHour() > 12){
                    timeDisplay.setText(String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",picker.getMinute())+" PM");
                }else {
                    timeDisplay.setText(picker.getHour()+" : " + picker.getMinute() + " AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

            }
        });


    }
}
