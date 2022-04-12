package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdatpter;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsList;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class CommentsActivity extends AppCompatActivity {
    private User user;
    private Meal meal;
    private List<Comment> comments;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        user = UserManager.getInstance().getCurrentUser();
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        comments = new ArrayList<>();
        loadCommentsInListview();
        ListView commentsListView = findViewById(R.id.listViewComments);
        commentsListView.setAdapter(new CommentsAdapter(this, comments));

        ((ImageView)findViewById(R.id.ImgPublishComments)).setOnClickListener(click -> {
            clickPublishComment(commentsListView);
        });

        this.initCommentActivity(meal);
    }

    private void initCommentActivity(Meal meal){
        //meal property
        /**ImageView imageView =  ((ImageView)findViewById( R.id.authorCommentPicture));
        Picasso.with(this).load(user.getImgUrl()).into(imageView);**/
        ((TextView)findViewById( R.id.mealCommentsName)).setText(meal.getName());

    }

    private void clickPublishComment(ListView commentsListView){
        String commentaryText = ((EditText)findViewById(R.id.textInputEditText)).getText().toString();
        comments.add(new Comment(commentaryText, user));
        meal.getComments().addAll(comments);
        updateCommentsToFirestore(meal);

        commentsListView.setAdapter(new CommentsAdapter(this, comments));

    }

    public void updateCommentsToFirestore(Meal meal){
        String mealNameCorrectFormat = meal.getName().replaceAll("[^a-zA-Z]+","").replaceAll(" ", "_").toLowerCase();

        db.collection("recipes").document(mealNameCorrectFormat)
                .update(convertToFirestoreFormat(meal));
    }

    public Map convertToFirestoreFormat(Meal meal){
        Map<String, Object> firestoreMeal = new HashMap<>();
        firestoreMeal.put("comments", meal.getComments());
        return firestoreMeal;
    }

    private void loadCommentsInListview(){
        String mealNameCorrectFormat = meal.getName().replaceAll("[^a-zA-Z]+","").replaceAll(" ", "_").toLowerCase();
        db.collection("recipes").document(mealNameCorrectFormat)
                .get()
                .addOnSuccessListener(s -> {
                    Meal mealInit = s.toObject(Meal.class);
                    comments.addAll(mealInit.getComments());
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
}
