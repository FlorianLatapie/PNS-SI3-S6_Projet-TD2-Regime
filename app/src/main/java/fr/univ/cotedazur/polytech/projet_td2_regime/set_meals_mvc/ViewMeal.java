package fr.univ.cotedazur.polytech.projet_td2_regime.set_meals_mvc;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import fr.univ.cotedazur.polytech.projet_td2_regime.R;

public class ViewMeal implements Observer {
    private final String TAG = "kure " + getClass().getSimpleName();
    private  ViewAdapter adapter;
    private ViewGroup layout;
    private boolean modelCreated = false;
    IViewClick controller;

    public <T extends ViewGroup> ViewMeal(Context context, T layout) {
        this.layout = layout;
        Log.d(TAG, "View is created" );
    }

    public void setListener(IViewClick controller) {
        this.controller = controller;
    }

    public void onClickItem(int position){
        controller.onClickItem(position);
    }

    public ViewGroup getLayout() {
        return layout;
    }

    @Override
    public void update(Observable observable, Object otherData) {
        ModelMeal model = (ModelMeal) observable;
        if (!modelCreated) {        //fist time only
            adapter.updateModel(model);
            ListView listView = ((ListView)layout.findViewById(R.id.listviewTeam1));
            listView.setAdapter(adapter);
            modelCreated = true;
        }
        else {
            adapter.refresh(model);
        }
        Log.d(TAG, "View update with ==> " + model);
    }
}
