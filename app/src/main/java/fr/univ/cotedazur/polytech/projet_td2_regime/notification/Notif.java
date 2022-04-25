package fr.univ.cotedazur.polytech.projet_td2_regime.notification;


import static android.provider.Settings.System.getString;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.protobuf.Any;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class Notif {
    Bitmap bmp = null;
    private String title;
    private String description;
    private Date date;
    private String image;
    private Meal meal;

    public Notif(String title, String description, String image, Meal meal){
        this.title = title;
        this.description = description;
        this.date = new Date();
        this.image = image;
        this.meal = meal;
        UserManager.getInstance().getCurrentUser().addNotification(this);
    }

    public void sendNotification(Context context){
        //bmp = getBitmapFromURL(meal.getImageLink());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, myApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_food_bank_24)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setLargeIcon(bmp)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bmp)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public Meal getMeal(){
        return this.meal;
    }
}
