package fr.univ.cotedazur.polytech.projet_td2_regime.create_meal;

import static android.content.ContentValues.TAG;
import static fr.univ.cotedazur.polytech.projet_td2_regime.create_meal.PermissionFactory.CAMERA_REQUEST_CODE;
import static fr.univ.cotedazur.polytech.projet_td2_regime.create_meal.PermissionFactory.IMAGE_PICK_GALLERY_CODE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.AbstractMealFactory;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.MealFactory;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class CreateMealActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView imagePreiew;
    private Bitmap image;
    private Button addPictureButton;
    private Button publishMealButton;
    private User user;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        user = UserManager.getInstance().getCurrentUser();

        imagePreiew = findViewById(R.id.imagePreview);

        addPictureButton = findViewById(R.id.addImageButton);
        addPictureButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateMealActivity.this);
            builder.setTitle("Choisissez une option");
            builder.setItems(new CharSequence[]{"Prendre une photo", "Choisir une photo de la galerie"}, (dialog, which) -> {
                switch (which) {
                    case 0:
                        callCameraAction();
                        break;
                    case 1:
                        openGallery();
                        break;
                }
            });
            builder.show();
        });

        publishMealButton = findViewById(R.id.publishButton);
        publishMealButton.setOnClickListener(v -> {
            try {
                publishMeal();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void publishMeal() throws Throwable {
        String name = ((TextView) findViewById(R.id.mealNameInput)).getText().toString();
        String ingredients = ((TextView) findViewById(R.id.mealIngredients)).getText().toString();
        int preparationTime = Integer.parseInt(((TextView) findViewById(R.id.mealTimePreparationInput)).getText().toString().equals("") ? "0" : ((TextView) findViewById(R.id.mealTimePreparationInput)).getText().toString());
        int nbOfPeople = Integer.parseInt(((TextView) findViewById(R.id.mealNumberOfPeopleInput)).getText().toString().equals("") ? "0" : ((TextView) findViewById(R.id.mealNumberOfPeopleInput)).getText().toString());
        String preparation = ((TextView) findViewById(R.id.mealPreparation)).getText().toString();
        int kcal = Integer.parseInt(((TextView) findViewById(R.id.mealKcalInput)).getText().toString().equals("") ? "0" : ((TextView) findViewById(R.id.mealKcalInput)).getText().toString());
        String authorName = this.user.getFirstName() + " " + this.user.getLastName();

        AbstractMealFactory mealFactory = new MealFactory();
        Meal meal = mealFactory.build(1, name, image, preparationTime, nbOfPeople, ingredients, preparation, kcal, authorName);

        List<String> errors = Meal.validate(meal);
        if (errors.size() > 0) {
            StringBuilder sb = new StringBuilder();
            int nbErrorsToShow = Math.min(errors.size(), 3);
            for (int i = 0; i < nbErrorsToShow; i++) {
                String error = errors.get(i);
                sb.append(error);
                if (i < nbErrorsToShow - 1) {
                    sb.append("\n");
                }
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        } else {
            System.out.println(meal);
            Toast.makeText(this, "Recette créée", Toast.LENGTH_LONG).show();
            this.addMealToFirestore(meal);
            super.finish();
        }
    }

    public void addMealToFirestore(Meal meal) {
        String mealNameCorrectFormat;
        mealNameCorrectFormat = meal.getName().replaceAll("[^a-zA-Z]+", "");
        mealNameCorrectFormat = mealNameCorrectFormat.replaceAll(" ", "_").toLowerCase();

        StorageReference mountainsRef = mStorageRef.child(mealNameCorrectFormat + ".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        meal.getPictureBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "Upload failed : " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_LONG).show();
            }
        });

        db.collection("recipes").document(mealNameCorrectFormat)
                .set(convertToFirestoreFormat(meal))
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

    public Map convertToFirestoreFormat(Meal meal) {
        Map<String, Object> firestoreMeal = new HashMap<>();

        firestoreMeal.put("name", meal.getName());
        firestoreMeal.put("preparationTime", meal.getPreparationTime());
        firestoreMeal.put("nbOfPeople", meal.getNbOfPeople());
        firestoreMeal.put("ingredients", meal.getIngredients());
        firestoreMeal.put("preparation", meal.getPreparation());
        firestoreMeal.put("kcal", meal.getKcal());
        firestoreMeal.put("likes", 0);
        firestoreMeal.put("comments", meal.getComments());
        firestoreMeal.put("authorName", meal.getAuthorName());

        return firestoreMeal;
    }

    public void callCameraAction() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            openCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera permission authorized !", Toast.LENGTH_SHORT).show();
                    openCamera();
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        // apparement il n'y a pas besoin d'autorisations pour la galerie "Manifest.permission.READ_EXTERNAL_STORAGE"
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }


    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                imagePreiew.setImageBitmap((Bitmap) data.getExtras().get("data"));
            } else if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                imagePreiew.setImageURI(data.getData());
            }
            image = (Bitmap) data.getExtras().get("data");
        }
    }
}
