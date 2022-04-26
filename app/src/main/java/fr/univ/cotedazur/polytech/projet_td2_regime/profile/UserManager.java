package fr.univ.cotedazur.polytech.projet_td2_regime.profile;
import static android.content.ContentValues.TAG;
import android.util.Log;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private static UserManager instance = null;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User currentUser;
    private User userFromFirestore;

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
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getUserFromFirestore(User currentUser) {
        System.out.println("User current "+currentUser);
        if(currentUser!=null) {
            String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
            DocumentReference docRef = db.collection("users").document(userNameCorrectFormat);
            System.out.println("docRef : " + docRef.get().isSuccessful());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        System.out.println("document : "+document);
                        if (document.exists()) {
                            userFromFirestore = (document.toObject(User.class));
                            System.out.println("User from firestore : "+userFromFirestore);
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        } else {
                            db.collection("users").document(userNameCorrectFormat).set(convertToFirestoreFormat(currentUser));
                            userFromFirestore = (currentUser);
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

            return userFromFirestore;
        }
        return null;
    }

    public void updateUserToFirestore(User currentUser) {
        String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
        db.collection("users").document(userNameCorrectFormat).update(convertToFirestoreFormat(currentUser));
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
