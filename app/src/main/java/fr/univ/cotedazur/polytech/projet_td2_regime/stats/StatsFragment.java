package fr.univ.cotedazur.polytech.projet_td2_regime.stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.meal.Meal;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.UserManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User currentUser;
    private Map<LocalDate, Double> weightHistorical;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        this.currentUser = UserManager.getInstance().getCurrentUser();

        if (currentUser != null) {

            weightDisplay(view);

            calorieDisplay(view);

            TextView tv = view.findViewById(R.id.textView6);
            tv.setText(currentUser.getWeightGoal() + " kg");

            createGraph(view);


        } else {
            GraphView graph = view.findViewById(R.id.graphView);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(100);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(10);
        }

        Button saveWeight = view.findViewById(R.id.buttonSave);
        saveWeight.setOnClickListener(click -> onClick(view));

        Button eatenMeals = view.findViewById(R.id.button);
        eatenMeals.setOnClickListener(click -> openEatenMeals(view));

        Button addToCalendar = view.findViewById(R.id.reminderButton);
        addToCalendar.setOnClickListener(click -> onClickAddEventCalendar(view));


        // Inflate the layout for this fragment
        return view;
    }

    private void openEatenMeals(View view) {
        if (isUserConnected()) {
            Intent intent = new Intent(getActivity().getApplicationContext(), EatenMealsActivity.class);
            getActivity().startActivity(intent);
        }
    }

    private void calorieDisplay(View view) {

        TextView tv = view.findViewById(R.id.textView10);
        tv.setText(currentUser.getCalorieGoal() + " kCal");

        List<Meal> eatenMeals = currentUser.getEatenMeals();
        int eatenCalories = 0;

        for (Meal meal : eatenMeals) {
            eatenCalories += meal.getKcal();
        }

        tv = view.findViewById(R.id.textView11);
        tv.setText(eatenCalories + " kCal");

    }

    private void weightDisplay(View view) {
        TextView tv = view.findViewById(R.id.textView7);
        tv.setText(currentUser.getWeight() + " kg");
    }

    private void createGraph(View view) {
        GraphView graph = view.findViewById(R.id.graphView);
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        this.weightHistorical = currentUser.getWeightHistory();
        int i = 0;

        for (Map.Entry<LocalDate, Double> entry : this.weightHistorical.entrySet()) {
            series.appendData(new DataPoint(i, entry.getValue().intValue()), false, this.weightHistorical.size(), false);
            i++;
        }
        series.setDrawDataPoints(true);
        graph.addSeries(series);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(Math.round(series.getHighestValueY() + 10));
        graph.getViewport().setMinX(0);
        if (i == 1) {
            graph.getViewport().setMaxX(series.getHighestValueX() + 4);
        } else {
            graph.getViewport().setMaxX(series.getHighestValueX() + 1);
        }
    }

    private void onClick(View view) {
        if (isUserConnected()) {
            EditText editText = view.findViewById(R.id.editText);
            if (!editText.getText().toString().equals("")) {
                Double newWeight = Double.parseDouble(editText.getText().toString());
                LocalDate lastDate = this.currentUser.getLastWeightDate();
                LocalDate sunday = lastDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                LocalDate newDate = LocalDate.now();

                if (newDate.isBefore(sunday)) {
                    weightHistorical.remove(lastDate);
                }

                weightHistorical.put(newDate, newWeight);
                currentUser.setWeightHistory(weightHistorical);
                currentUser.setWeight(newWeight);
            }
            createGraph(view);
            weightDisplay(view);
        }

    }

    private boolean isUserConnected() {
        if (this.currentUser == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage("Connectez-vous");
            builder.show();
            return false;
        }
        return true;
    }

    private void onClickAddEventCalendar(View view) {
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        // adds a new calendar event for the next day at 7:00 am
        Date date = new Date();
        date.setHours(7);
        date.setMinutes(0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calendar.getTimeInMillis());
        intent.putExtra("allDay", false);
        intent.putExtra("endTime", calendar.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "Renseignez votre poids dans l'application");
        getActivity().startActivity(intent);
    }
}