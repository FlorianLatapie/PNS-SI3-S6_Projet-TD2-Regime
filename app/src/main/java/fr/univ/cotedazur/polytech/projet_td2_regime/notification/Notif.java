package fr.univ.cotedazur.polytech.projet_td2_regime.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class Notif {
    Bitmap bmp = null;
    private String title;
    private String description;
    private Date date;
    private String image;
    private Meal meal;

    public Notif(String title, String description, String image, Meal meal) {
        this.title = title;
        this.description = description;
        this.date = new Date();
        this.image = image;
        this.meal = meal;
        //this.bmp = getBitmapFromURL("https://fac.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2FFAC.2Fvar.2Ffemmeactuelle.2Fstorage.2Fimages.2Fcuisine.2Frecettes-de-cuisine.2Fun-repas-ete-pour-10-personnes-40995.2F14609271-1-fre-FR.2Fun-repas-d-ete-pour-10-personnes.2Ejpg/750x562/quality/80/crop-from/center/cr/wqkgVGhpbnN0b2NrIC8gRmVtbWUgQWN0dWVsbGU%3D/recettes-repas-d-ete-pour-10-personnes.jpeg");

        UserManager.getInstance().getCurrentUser().addNotification(this);
    }

    public void sendNotification(Context context) {
        Intent resultIntent = new Intent(context, MealActivity.class);
        resultIntent.putExtra("Meal", meal);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 1, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        this.bmp= BitmapFactory.decodeResource(context.getResources(), R.drawable.pancake);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, myApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_food_bank_24)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bmp)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());
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

    public Meal getMeal() {
        return this.meal;
    }
}
