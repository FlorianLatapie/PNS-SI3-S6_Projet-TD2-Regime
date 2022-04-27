package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.CommentsActivity;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.notification.NotificationActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.DownloadImageTask;

//Détail du repas
public class MealActivity extends AppCompatActivity {

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
        initMealActivity();

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

        ((ImageButton) findViewById(R.id.imageClockButton)).setOnClickListener(click -> {
            onTimerNotification();
        });
    }



    private void initMealActivity(){
        //meal property
        String prepTime = String.valueOf(meal.getPreparationTime());
        String calories = String.valueOf(meal.getKcal());
        String nbPeople = String.valueOf(meal.getNbOfPeople());

        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());

        ImageView mealPicture = findViewById( R.id.imageMeal);
        if(meal.getImageLink() == null){
            mealPicture.setImageResource(meal.getPicture());
        } else {
            new DownloadImageTask(mealPicture, meal.getImageLink()).execute();
        }

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
        if(isUserConnected()){
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
            UserManager.getInstance().updateUserToFirestore(user);
        }
    }

    private void onTimerNotification() {
        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.putExtra("Meal", meal);
        startActivity(intent);
    }


    private void onCommentsClick(){
        if(isUserConnected()){
            Intent intent = new Intent(MealActivity.this, CommentsActivity.class);
            intent.putExtra("Meal", getIntent().getIntExtra("Meal", 0));
            startActivity(intent);

        }
    }

    private void onEatItClick(){
        if(isUserConnected()){
            meal.increaseEatIt();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            meal.setDateAte(format.format(new Date()));
            user.getEatenMeals().add(meal);
            UserManager.getInstance().updateUserToFirestore(user);
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

}