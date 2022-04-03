package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.CommentsActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

//Détail du repas
public class MealActivity extends AppCompatActivity {
    TextView mealNameFirebase;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private User user;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        user = UserManager.getInstance().getCurrentUser();
        meal = MealsList.get(getIntent().getIntExtra("Meal", 0));
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
    }
    
    private void initMealActivity(){
        //meal property
        ((TextView)findViewById( R.id.mealName)).setText(meal.getName());
        ((ImageView)findViewById( R.id.imageMeal)).setImageResource(meal.getPicture());
        ((TextView)findViewById( R.id.mealTimePreparation)).setText(meal.getPreparationTime()+ "mn");
        ((TextView)findViewById( R.id.mealKcal)).setText(meal.getKcal()+" kcal");
        ((TextView)findViewById( R.id.mealNumberOfPeople)).setText(meal.getNbOfPeople()+" peoples");
        ((TextView)findViewById( R.id.mealIngredients)).setText(meal.getIngredients());
        ((TextView)findViewById( R.id.mealPreparation)).setText(meal.getPreparation());

        mealNameFirebase = findViewById( R.id.mealNameFirebase);

        DocumentReference documentReference = db.collection("recipes").document("recipe");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                mealNameFirebase.setText(value.getString("name"));
            }
        });

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
        }
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
}