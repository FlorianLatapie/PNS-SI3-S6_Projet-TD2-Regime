package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.CommentsActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.QueryListener;

import java.util.concurrent.Executor;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.notification.NoticationActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

//Détail du repas
public class MealActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User user;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("MealActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Intent intent = getIntent();

        user = UserManager.getInstance().getCurrentUser();
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        loadMealinListview(meal);
        //meal like increase

        ((LinearLayout)findViewById(R.id.likeLayout)).setOnClickListener(click -> {
            onLikeClick();
        });

        //meal comment section
        ((LinearLayout)findViewById(R.id.commentsLayout)).setOnClickListener(click -> {
            onCommentsClick();
        });


        ((Button)findViewById(R.id.eatIt)).setOnClickListener(click -> {
            onEatItClick();
        });
    }
    
    private void initMealActivity(Meal meal){
        //meal property
        String prepTime = String.valueOf(meal.getPreparationTime());
        String calories = String.valueOf(meal.getKcal());
        String nbPeople = String.valueOf(meal.getNbOfPeople());

        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());
        ((ImageView)findViewById( R.id.imageMeal)).setImageResource(meal.getPicture());
        ((TextView)findViewById( R.id.mealTimePreparation)).setText(prepTime + "mn");
        ((TextView)findViewById( R.id.mealKcal)).setText(calories + " kcal");
        ((TextView)findViewById( R.id.mealNumberOfPeople)).setText(nbPeople + " personnes");
        ((TextView)findViewById( R.id.mealIngredients)).setText(meal.getIngredients());
        ((TextView)findViewById( R.id.mealPreparation)).setText(meal.getPreparation());

        //meal init reactions
        ((TextView) findViewById(R.id.mealLikes)).setText(meal.getLikes()+" likes");
        ((TextView)findViewById( R.id.mealComments)).setText(meal.getComments().size()+" comments");
        ((TextView)findViewById( R.id.mealAuthor)).setText(meal.getAuthorName());
    }

    private void onLikeClick(){
        showNotificationWithImage();
        if(isUserConnected()){
            //showNotificationWithImage();
            if(!hasUserLikeTheMeal(meal)){
                user.getLikeMeals().add(meal);
                meal.increaseLikes();
                ((TextView) findViewById(R.id.mealLikes)).setText(meal.getLikes()+" likes");
            }
            else{
                user.getLikeMeals().remove(meal);
                meal.decreaseLikes();
                ((TextView) findViewById(R.id.mealLikes)).setText(meal.getLikes()+" likes");
            }
            updateUserToFirestore(user);
            updateMealToFirestore(meal);
        }
    }

    private void onCommentsClick(){
        if(isUserConnected()){
            Intent intent = new Intent(MealActivity.this, CommentsActivity.class);
            intent.putExtra("Meal", meal);
            startActivity(intent);
        }
    }

    private void onEatItClick(){
        if(isUserConnected()){
            meal.increaseEatIt();
            user.getEatenMeals().add(meal);
            int nbOfTimeThisMealHasBeenAte = user.getEatenMeals().stream().filter(m-> m.equals(meal)).toArray().length;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(meal.getName()+" mangé " + nbOfTimeThisMealHasBeenAte +" fois");
            builder.show();
        }
    }

    private boolean isUserConnected(){
        if(user==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Connectez-vous");
            builder.show();
            return false;
        }
        return true;
    }

    private boolean hasUserLikeTheMeal(Meal meal){
        if(user.getLikeMeals().contains(meal)) return true;
        return false;
    }

    @SuppressLint("StaticFieldLeak")
    private void showNotificationWithImage(){
        new AsyncTask<String, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... strings) {
                InputStream inputStream;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.connect();
                    inputStream = connection.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);
                } catch (Exception ignored) {

                }
                return null;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap){
                showNotification(bitmap);
            }
        }.execute("https://m1.quebecormedia.com/emp/cdp_prod/coup_de_pouce-_-e9aecfbf44f902f1d9b611393e8a3aac67a1b3bb-_-bol-repas.jpg");
    }

    private void showNotification(Bitmap bitmap) {
        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_2";

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), NoticationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(),
                channelId

        );
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("Votre plat du jour");
        builder.setContentText("Voila une petite recette à cuisiner");
        // builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Le langage est la capacité d'exprimer une pensée et de communiquer au moyen d'un système de signes (vocaux, gestuel, graphiques, tactiles, olfactifs, etc.) doté d'une sémantique, et le plus souvent d'une syntaxe — mais ce n'est pas systématique (la cartographie est un exemple de langage non syntaxique). Fruit d'une acquisition, la langue est une des nombreuses manifestations du langage."));
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager != null && notificationManager.getNotificationChannel(channelId)==null){
                NotificationChannel notificationChannel = new NotificationChannel(
                        channelId,
                        "Notification Channel 1",
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription("This notifcation channel is used notify user");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = builder.build();
        if(notificationManager != null){
            notificationManager.notify(notificationId, notification);
        }

    }

    public void updateMealToFirestore(Meal meal){
        String mealNameCorrectFormat;
        mealNameCorrectFormat = meal.getName().replaceAll("[^a-zA-Z]+","");
        mealNameCorrectFormat = mealNameCorrectFormat.replaceAll(" ", "_").toLowerCase();
        db.collection("recipes").document(mealNameCorrectFormat)
                .update(convertToFirestoreFormat(meal))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void updateUserToFirestore(User user){
        String userNameCorrectFormat = user.getFirstName()+user.getLastName().replaceAll("[^a-zA-Z]+","").replaceAll(" ", "_").toLowerCase();
        db.collection("users").document(userNameCorrectFormat)
                .update(convertToFirestoreFormat(user))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public Map convertToFirestoreFormat(Meal meal){
        Map<String, Object> firestoreMeal = new HashMap<>();
        firestoreMeal.put("likes", meal.getLikes());
        firestoreMeal.put("comments", meal.getComments());

        return firestoreMeal;
    }

    public Map convertToFirestoreFormat(User user){
        Map<String, Object> firestoreUser = new HashMap<>();
        firestoreUser.put("likeMeals", user.getLikeMeals());
        return firestoreUser;
    }

    private void loadMealinListview(Meal meal) {
        String mealNameCorrectFormat;
        mealNameCorrectFormat = meal.getName().replaceAll("[^a-zA-Z]+","");
        mealNameCorrectFormat = mealNameCorrectFormat.replaceAll(" ", "_").toLowerCase();

        db.collection("recipes").document(mealNameCorrectFormat)
                .get()
                .addOnSuccessListener(s -> {
                    Meal mealInit = s.toObject(Meal.class);
                    initMealActivity(mealInit);
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private void updateMeal(Meal mealUpdated){ this.meal = mealUpdated;}

}