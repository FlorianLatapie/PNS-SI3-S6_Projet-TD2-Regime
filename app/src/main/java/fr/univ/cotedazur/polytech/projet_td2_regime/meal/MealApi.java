package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MealApi extends AsyncTask<Void, Void, List<Meal>> {
    private static final String app_id = "52744ee2";
    private static final String app_key = "3567d66bc3e1eddcd42d76e0804cf57a";
    private List<Meal> mealsFromApi = new ArrayList<>();
    private String query;
    Activity activity;
    ListView listView;

    public MealApi(String query, Activity activity, ListView listView){
        this.query = query;
        this.activity = activity;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Meal> doInBackground(Void... params) {
        List<Meal> meals = new ArrayList<>();
        String url = "https://api.edamam.com/api/recipes/v2?type=public&q=" + getQuery() + "&app_id=" + app_id + "&app_key=" + app_key
                + "&random=true&field=label&field=image&field=dietLabels&field=healthLabels&field=ingredientLines&field=calories&field=totalTime";
        try {
            URL recipesEndpoint = new URL(url);
            // Create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) recipesEndpoint.openConnection();

            if (myConnection.getResponseCode() == 200) {
                InputStream in = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(in, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                meals.addAll(readJson(jsonReader));
                // return the data to onPostExecute method
                return meals;
            } else {
                Log.e("MealApi", "API_ERROR : RESPONSE CODE NOT 200");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return meals;
    }

    public List<Meal> readJson(JsonReader jsonReader) throws Throwable {
        List<Meal> mealList = new ArrayList<>();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String key = jsonReader.nextName();
            if (key.equals("hits")) {
                mealList.addAll(readRecipes(jsonReader));
                break;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.close();
        return mealList;
    }

    public List<Meal> readRecipes(JsonReader reader) throws Throwable {
        List<Meal> mealList = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            mealList.add(readRecipe(reader));
        }
        reader.endArray();
        return mealList;
    }

    public Meal readRecipe(JsonReader reader) throws Throwable {
        Meal meal = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String value = reader.nextName();
            if (value.equals("recipe")) {
                meal = readInsideRecipe(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return meal;
    }

    public Meal readInsideRecipe(JsonReader reader) throws Throwable {
        String name = "";
        String image = "";
        String ingredients = "";
        String dietLabels = "";
        double calories = 0.0;
        int prepTime = 0;
        String author = "API";
        int nbOfPeople = 4;

        reader.beginObject();
        while (reader.hasNext()) {
            String value = reader.nextName();
            if (value.equals("label")) {
                name += reader.nextString();
            } else if(value.equals("image")){
                image += reader.nextString();
            } else if(value.equals("ingredientLines")){
                ingredients += readStringArray(reader);
            } else if(value.equals("dietLabels")){
                dietLabels += readStringArray(reader);
            } else if(value.equals("calories")){
                calories = reader.nextDouble();
            } else if(value.equals("totalTime")){
                prepTime = reader.nextInt();
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Meal meal =  MealFactory.build(1, name, image, prepTime, nbOfPeople, ingredients, ingredients, (int) calories, author);
        return meal;
    }

    public String readStringArray(JsonReader reader) throws IOException {
        String stringToWrite = "";

        reader.beginArray();
        while (reader.hasNext()) {
            stringToWrite += reader.nextString() + "\n";
        }
        reader.endArray();
        return stringToWrite;
    }

    public String getQuery() {
        return query;
    }

}
