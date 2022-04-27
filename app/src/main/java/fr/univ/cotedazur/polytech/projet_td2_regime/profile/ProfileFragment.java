package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MyMealsAdapter;
import fr.univ.cotedazur.polytech.projet_td2_regime.login.LoginActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.util.ExpandableHeightGridView;


public class ProfileFragment extends Fragment {

    private User currentUser;
    ExpandableHeightGridView gridView;
    ArrayList<Meal> mealsList;
    FirebaseFirestore db;

    public ProfileFragment() {
        // Required empty public constructor
        currentUser = new User("Bob", "Dylan", Gender.HOMME, 20, 181, "My awesome bio !", Diet.PROTEIN, 82.0, 75.0, R.drawable.bob);
    }


    private void disconnectUer() {
        UserManager.getInstance().setCurrentUser(null);
        Toast.makeText(getContext(), "Vous êtes à présent déconnecté !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView firstName = view.findViewById(R.id.profilFirstName);
        firstName.setText(currentUser.getFirstName());

        TextView lastName = view.findViewById(R.id.profilLastName);
        lastName.setText(currentUser.getLastName());

        TextView bio = view.findViewById(R.id.profilBio);
        bio.setText(currentUser.getBio());

        view.findViewById(R.id.seDeconnecter).setOnClickListener(v -> {
            disconnectUer();
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            getActivity().startActivity(intent);
        });

        view.findViewById(R.id.monRegime).setOnClickListener(
                v -> {
                    Intent intent = new Intent(getActivity().getApplicationContext(), MyDietActivity.class);
                    getActivity().startActivity(intent);
                }
        );

        gridView = (ExpandableHeightGridView) view.findViewById(R.id.expandableHeightGridView);
        gridView.setExpanded(true);

        mealsList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        loadUserMeals();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Meal meal = (Meal) o;
                Intent intent = new Intent(getActivity().getApplicationContext(), MealActivity.class);
                intent.putExtra("Meal", meal);
                getActivity().startActivity(intent);
            }

        });

        return view;
    }


    private void loadUserMeals() {
        db.collection("recipes")
                .whereEqualTo("authorName", "Bob Dylan")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Meal meal = d.toObject(Meal.class);
                                mealsList.add(meal);
                            }
                            MyMealsAdapter adapter = new MyMealsAdapter(getActivity().getApplicationContext(), mealsList);
                            gridView.setAdapter(adapter);
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

    public User getCurrentUser() {
        return currentUser;
    }
}