package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance = null;

    private User currentUser;
    private User userFromFirestore;
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
        if(currentUser!=null) {currentUser = getUserFromFirestore(currentUser);}

        return currentUser;
    }

    public void setCurrentUser(User currentUser) { //set current user if exists
        this.currentUser = currentUser;
        if(currentUser!=null) {
           this.currentUser =  getUserFromFirestore(currentUser);
           //addUserToFirestore(currentUser);

        } else this.currentUser = null;

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

    public User getUserFromFirestore(User currentUser) {
        String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
        DocumentReference docRef = db.collection("users").document(userNameCorrectFormat);
        docRef.get()
            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    userFromFirestore =  documentSnapshot.toObject(User.class);
                }
             })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    userFromFirestore = null;
                }
            });
        return userFromFirestore;
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
