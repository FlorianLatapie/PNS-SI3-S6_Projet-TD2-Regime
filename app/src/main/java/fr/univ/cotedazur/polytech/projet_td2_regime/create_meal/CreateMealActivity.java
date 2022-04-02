package fr.univ.cotedazur.polytech.projet_td2_regime.create_meal;

import static fr.univ.cotedazur.polytech.projet_td2_regime.create_meal.IPictureActivity.CAMERA_REQUEST_CODE;
import static fr.univ.cotedazur.polytech.projet_td2_regime.create_meal.IPictureActivity.IMAGE_PICK_GALLERY_CODE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.Meal;

public class CreateMealActivity extends AppCompatActivity {
    private ImageView imagePreiew;
    private Button addPictureButton;
    private Button publishMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
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
        }
    }
}
