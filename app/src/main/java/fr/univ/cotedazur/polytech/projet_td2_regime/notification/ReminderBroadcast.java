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

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class ReminderBroadcast extends BroadcastReceiver {
    Bitmap bmp = null;
    @Override
    public void onReceive(Context context, Intent intent){

       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               bmp = getBitmapFromURL("https://m1.quebecormedia.com/emp/cdp_prod/coup_de_pouce-_-e9aecfbf44f902f1d9b611393e8a3aac67a1b3bb-_-bol-repas.jpg");
           }
       });

        thread.start();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyMeal")
                .setSmallIcon(R.drawable.ic_baseline_food_bank_24)
                .setContentTitle("Meal")
                .setContentText("Je suis un repas que tu dois cuisiner")
                .setAutoCancel(true)
                .setLargeIcon(bmp)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bmp)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

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
}
