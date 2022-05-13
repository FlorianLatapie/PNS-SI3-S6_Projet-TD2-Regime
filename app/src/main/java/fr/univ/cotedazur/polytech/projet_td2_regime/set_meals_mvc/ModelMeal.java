package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals_mvc;

import android.app.Activity;
import android.util.Log;
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

import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.MealApi;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.Util;

public class ModelMeal extends Observable {
    private FirebaseFirestore db;
    private final String TAG = "kure " + getClass().getSimpleName();
    private ArrayList<Meal> mealsList;
    private Controller controller;
    private Activity activity;

    public ModelMeal(Controller controller){
        mealsList = new ArrayList<>();
    }

    public void setController(Controller controller) {
        this.controller = controller;
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
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error");
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

    public Object get(int position) {
        return mealsList.get(position);
    }

    public void remove(int index) {
        if (index<mealsList.size()) {
            mealsList.remove(index);
            setChanged();
            notifyObservers();
        }
    }

    public int size() {
        return mealsList.size();
    }
}
