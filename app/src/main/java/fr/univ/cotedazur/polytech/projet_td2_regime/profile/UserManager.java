package fr.univ.cotedazur.polytech.projet_td2_regime.profile;
import static android.content.ContentValues.TAG;

import static com.google.android.gms.tasks.Tasks.await;

import android.util.Log;
import android.webkit.JsResult;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;

public class UserManager {
    private static UserManager instance = null;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User currentUser;
    private User userFromFirestore;
    private TaskCompletionSource<Void> delaySource = new TaskCompletionSource<>();
    private Task delayTask = delaySource.getTask();

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

    public void getUserFromFirestore(String username){
        System.out.println("User current "+currentUser);
            //String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
            DocumentReference docRef = db.collection("users").document(username);
            System.out.println("USER TEST:" + username);
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    ArrayList<Meal> eatenMeals = new ArrayList<>();
                    HashMap datamap = (HashMap) documentSnapshot.getData();
                    System.out.println(datamap);
                    ArrayList eatenMealsDoc = ((ArrayList)datamap.get("eatenMeals"));
                    eatenMealsDoc.forEach(d -> {
                        Map<String,Object> doc;
                        doc = (Map<String,Object>) d;
                        int preparationTime = Integer.parseInt(String.valueOf((Long)doc.get("preparationTime")));
                        int nbOfPeople = Integer.parseInt(String.valueOf((Long) doc.get("nbOfPeople")));
                        int kcal = Integer.parseInt(String.valueOf((Long) doc.get("kcal")));
                        System.out.println("prepar " + preparationTime + " nbPeople " + nbOfPeople + " kcal "+ kcal);
                        Meal meal = new Meal((String) doc.get("name"), (String) doc.get("imageLink"), preparationTime, nbOfPeople, (String) doc.get("ingredients"), (String) doc.get("preparation"), kcal, (String) doc.get("authorName"));
                        eatenMeals.add(meal);
                        System.out.println(meal.getImageLink());
                    });
                    userFromFirestore = documentSnapshot.toObject(User.class);
                    User newUser = new User(userFromFirestore);
                    newUser.setEatenMeals(eatenMeals);
                    currentUser = newUser;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("","ERREUR ICI:" + e);
                }
            });
    }

    public void updateUserToFirestore(User currentUser) {
        String userNameCorrectFormat = currentUser.getFirstName() + currentUser.getLastName().replaceAll("[^a-zA-Z]+", "").replaceAll(" ", "_").toLowerCase();
        db.collection("users").document(userNameCorrectFormat).update(convertToFirestoreFormat(currentUser));
    }

    public Map convertToFirestoreFormat(User user){
        Map<String, Object> firestoreUser = new HashMap<>();
        firestoreUser.put("firstName", user.getFirstName());
        firestoreUser.put("lastName", user.getLastName());
        firestoreUser.put("gender", user.getGender());
        firestoreUser.put("age", user.getAge());
        firestoreUser.put("age", user.getSize());
        firestoreUser.put("bio", user.getBio());
        firestoreUser.put("diet", user.getDiet());
        firestoreUser.put("weight", user.getWeight());
        firestoreUser.put("weightGoal", user.getWeightGoal());
        firestoreUser.put("imageProfile", user.getImageProfile());
        firestoreUser.put("publishedMeals", user.getPublishedMeals());
        firestoreUser.put("likeMeals", user.getLikeMeals());
        firestoreUser.put("eatenMeals", user.getEatenMeals());
        firestoreUser.put("notifications", user.getNotifications());
        return firestoreUser;
    }
}
