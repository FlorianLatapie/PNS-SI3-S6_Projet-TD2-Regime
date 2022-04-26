package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.IListner;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.home.MealActivity;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeFragment extends Fragment implements IListner {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User user;

    public LikeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public LikeFragment newInstance(String param1, String param2) {
        LikeFragment fragment = new LikeFragment();
        this.user = UserManager.getInstance().getCurrentUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        if (this.user != null) {
            LikeAdapter adapter = new LikeAdapter(getActivity().getApplicationContext());

            //Récupération du composant ListView
            ListView list = view.findViewById(R.id.listLikeView);

            //Initialisation de la liste avec les données

            list.setAdapter(adapter);

            adapter.addListener(this);

            //return inflater.inflate(R.layout.meal_fragment, container, false);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = list.getItemAtPosition(position);
                    Meal meal = (Meal) o;
                    Intent intent = new Intent(getActivity().getApplicationContext(), MealActivity.class);
                    intent.putExtra("Meal", meal);
                    getActivity().startActivity(intent);
                }
            });
        }
        return view;
    }

    @Override
    public void onClickMeal(Meal meal) {
        Intent intent = new Intent(getActivity().getApplicationContext(), MealActivity.class);
        intent.putExtra("Meal", meal);
        getActivity().startActivity(intent);
    }
}