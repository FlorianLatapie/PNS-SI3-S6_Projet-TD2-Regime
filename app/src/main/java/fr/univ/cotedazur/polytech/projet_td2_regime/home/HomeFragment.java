package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.MealApi;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.Util;


public class HomeFragment extends Fragment implements IListner {
    ListView listView;
    ArrayList<Meal> mealsList;
    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        //Récupération du composant ListView
        listView = view.findViewById(R.id.listView);

        mealsList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        loadMealsFromApi();
        loadMealsinListview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Meal meal = (Meal) o;
                Intent intent = new Intent(getActivity().getApplicationContext(), MealActivity.class);
                intent.putExtra("Meal", meal);
                getActivity().startActivity(intent);
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume of HomeFragment");
        super.onResume();
    }

    @Override
    public void onClickMeal(Meal meal) {
        Intent intent = new Intent(getActivity().getApplicationContext(), MealActivity.class);
        intent.putExtra("Meal", meal);
        getActivity().startActivity(intent);
    }

    private void loadMealsFromApi(){
        User currentUser = UserManager.getInstance().getCurrentUser();
        String userDiet;
        if (currentUser == null){
            userDiet = "healthy";
        } else {
            userDiet = Util.replaceSpace(currentUser.getDiet().getEnglishName());
        }

        MealApi mealApi = new MealApi(userDiet, getActivity(), listView);
        try {
            mealsList.addAll(mealApi.execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadMealsinListview() {
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
                            MealsAdatpter adapter = new MealsAdatpter(getActivity().getApplicationContext(), mealsList);
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Pas de données dans la base", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Erreur du chargement des données..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setMealsList(ArrayList<Meal> mealsList) {
        this.mealsList = mealsList;
    }
}