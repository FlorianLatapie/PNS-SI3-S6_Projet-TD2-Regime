package fr.univ.cotedazur.polytech.projet_td2_regime.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class LoginActivity extends AppCompatActivity {
    public String username = "Bobdylan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickSignUpUser(View view) {
        UserManager.getInstance().getUserFromFirestore(username);
        Toast.makeText(getApplicationContext(), "Vous êtes à présent connecté !", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickConnectUser(View view) {
        UserManager.getInstance().getUserFromFirestore(username);
        Toast.makeText(getApplicationContext(), "Vous êtes à présent connecté !", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}