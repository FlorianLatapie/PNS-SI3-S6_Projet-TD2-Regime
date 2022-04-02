package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdatpter;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsList;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class CommentsActivity extends AppCompatActivity {
    private User user;
    private Meal meal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        user = UserManager.getInstance().getCurrentUser();
        meal = MealsList.get(getIntent().getIntExtra("Meal", 0));

        ((ImageView)findViewById(R.id.ImgPublishComments)).setOnClickListener(click -> {

        });

        this.initCommentActivity();
    }

    private void initCommentActivity(){
        //meal property
        /**ImageView imageView =  ((ImageView)findViewById( R.id.authorCommentPicture));
        Picasso.with(this).load(user.getImgUrl()).into(imageView);**/
        ((TextView)findViewById( R.id.mealCommentsName)).setText(meal.getName());

    }
}
