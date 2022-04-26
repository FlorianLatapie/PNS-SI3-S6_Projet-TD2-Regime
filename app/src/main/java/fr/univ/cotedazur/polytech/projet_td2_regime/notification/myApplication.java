package fr.univ.cotedazur.polytech.projet_td2_regime.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;

public class myApplication extends Application {
    public static final String CHANNEL_HIGH = "Channel_High";
    public static Meal meal;

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannelHigh("High", "With Picture", NotificationManager.IMPORTANCE_HIGH);
    }

    private void createNotificationChannelHigh(String name, String description, int importance) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_HIGH, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
