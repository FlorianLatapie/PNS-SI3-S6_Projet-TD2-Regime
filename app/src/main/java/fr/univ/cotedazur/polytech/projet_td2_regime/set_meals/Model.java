package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals;

import android.app.Activity;
import android.widget.ActionMenuView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutionException;

import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdatpter;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.MealApi;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.Util;

public class Model extends Observable {
    private ListView listView;
    private ArrayList<Meal> mealsList;
    private FirebaseFirestore db;
    private Activity activity;

    public Model(Activity activity){
        this.activity = activity;
        loadMealsFromApi();
        loadMealsinListview();
    }

    public void loadMealsFromApi(){
        User currentUser = UserManager.getInstance().getCurrentUser();
        String userDiet;
        if (currentUser == null){
            userDiet = "healthy";
        } else {
            userDiet = Util.replaceSpace(currentUser.getDiet().getEnglishName());
        }

        MealApi mealApi = new MealApi(userDiet, this.activity, this.listView);
        try {
            mealsList.addAll(mealApi.execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadMealsinListview() {
        db.collection("recipes").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Meal meal = d.toObject(Meal.class);
                                mealsList.add(meal);
                            }
                            /*Controller adapter = new Controller(activity.getApplicationContext(), mealsList);
                            listView.setAdapter(adapter);*/
                        } else {
                            Toast.makeText(activity.getApplicationContext(), "Pas de données dans la base", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity.getApplicationContext(), "Erreur du chargement des données..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Meal> getMealsList() {
        return mealsList;
    }

    public void addItemToList(int i){
        this.getMealsList().add(i,new Meal());
        notifyObservers();
    }

    public void addNewMeal(){
        controler.addItemToList(new Meal());
    }
}
