package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private User currentUser;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
        currentUser = new User("Bob", "Dylan", Genre.HOMME, 20, 181, "My awesome bio !", Diet.PROTEIN, 82.0, 75.0, R.drawable.bob);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void disconnectUer() {
        UserManager.getInstance().setCurrentUser(null);
        Toast.makeText(getContext(), "Vous êtes à présent déconnecté !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        return view;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}