package fr.univ.cotedazur.polytech.projet_td2_regime.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fr.univ.cotedazur.polytech.projet_td2_regime.MainActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.Diet;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.Gender;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

public class LoginActivity extends AppCompatActivity {
    public User currentUser = new User("Bob", "Dylan", Gender.HOMME, 20, 181, "My awesome bio !",Diet.PROTEIN, 82.0, 75.0,R.drawable.userdefault);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickSignUpUser(View view){
        UserManager.getInstance().setCurrentUser(currentUser);
        Toast.makeText(getApplicationContext(), "Vous êtes à présent connecté !", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickConnectUser(View view){
        UserManager.getInstance().setCurrentUser(currentUser);
        Toast.makeText(getApplicationContext(), "Vous êtes à présent connecté !", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}