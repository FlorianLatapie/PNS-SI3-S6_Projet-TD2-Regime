package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealsAdatpter;

public class UserManager {
    private static UserManager instance = null;

    private User currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private UserManager(){
        currentUser = null;
    }

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        if(currentUser!=null) {getUserFromFirestore(); System.out.println("user likeMeals: "+currentUser.getLikeMeals());}

        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        if(currentUser!=null) {
            getUserFromFirestore();
            addUserToFirestore(currentUser);
        }

    }

    public void addUserToFirestore(User user){
        String userNameCorrectFormat = user.getFirstName()+user.getLastName().replaceAll("[^a-zA-Z]+","").replaceAll(" ", "_").toLowerCase();
        db.collection("users").document(userNameCorrectFormat)
                .set(convertToFirestoreFormat(user),  SetOptions.merge())
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

    public void getUserFromFirestore() {

        String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
        DocumentReference docRef = db.collection("users").document(userNameCorrectFormat);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(User.class);
            }
        });

    }

    public Map convertToFirestoreFormat(User user){
        Map<String, Object> firestoreUser = new HashMap<>();
        firestoreUser.put("firstName", user.getFirstName());
        firestoreUser.put("lastName", user.getLastName());
        firestoreUser.put("bio", user.getBio());
        firestoreUser.put("diet", user.getDiet());
        firestoreUser.put("weight", user.getWeight());
        firestoreUser.put("imageProfile", user.getImageProfile());
        firestoreUser.put("publishedMeals", user.getPublishedMeals());
        firestoreUser.put("likeMeals", user.getLikeMeals());
        firestoreUser.put("eatenMeals", user.getEatenMeals());

        return firestoreUser;
    }
}
