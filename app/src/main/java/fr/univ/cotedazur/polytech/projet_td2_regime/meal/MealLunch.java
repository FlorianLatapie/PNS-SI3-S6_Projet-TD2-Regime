package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

import android.graphics.Bitmap;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

import fr.univ.cotedazur.polytech.projet_td2_regime.Interactions.Comment;
import fr.univ.cotedazur.polytech.projet_td2_regime.R;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.Diet;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.Gender;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;

public class MealLunch extends Meal {
    private String name;
    private int picture;
    private Bitmap pictureBitmap;
    private String imageLink;
    private int preparationTime;
    private int nbOfPeople;
    private String ingredients;
    private String preparation;
    private int kcal;
    private int eatIt;
    private int likes;
    private List<Comment> comments;
    private String authorName;

    public MealLunch(String name, int picture) {
        this.name = name;
        this.picture = picture;
        this.ingredients = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nullam vehicula ipsum a arcu cursus. Amet volutpat consequat mauris nunc congue nisi vitae. Mauris sit amet massa vitae. Pellentesque elit ullamcorper dignissim cras. Lectus urna duis convallis convallis tellus id interdum velit. Vestibulum lectus mauris ultrices eros in cursus turpis. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada nunc vel risus commodo viverra. Dolor sed viverra ipsum nunc aliquet bibendum enim. Dui ut ornare lectus sit amet est. Arcu dui vivamus arcu felis.Aenean euismod elementum nisi quis eleifend quam adipiscing. Nisi porta lorem mollis aliquam ut porttitor. Convallis aenean et tortor at risus viverra adipiscing. Turpis in eu mi bibendum. In hendrerit gravida rutrum quisque non. Faucibus nisl tincidunt eget nullam non nisi est sit. Quis viverra nibh cras pulvinar mattis. Maecenas sed enim ut sem viverra. Fermentum et sollicitudin ac orci. Iaculis nunc sed augue lacus viverra. Tempus egestas sed sed risus. Amet tellus cras adipiscing enim eu turpis egestas pretium. Rhoncus mattis rhoncus urna neque. Sem et tortor consequat id porta nibh venenatis cras. Et magnis dis parturient montes nascetur. Lectus quam id leo in vitae turpis massa sed. Volutpat est velit egestas dui id ornare arcu. Purus sit amet volutpat consequat mauris nunc congue nisi. In cursus turpis massa tincidunt. Tellus in hac habitasse platea dictumst vestibulum rhoncus. Placerat in egestas erat imperdiet. Dolor sit amet consectetur adipiscing elit duis tristique. Non quam lacus suspendisse faucibus. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Et netus et malesuada fames ac. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque habitant. Quam elementum pulvinar etiam non quam lacus suspendisse. Interdum velit euismod in pellentesque massa. Turpis tincidunt id aliquet risus feugiat in ante.Netus et malesuada fames ac turpis egestas integer. Volutpat consequat mauris nunc congue nisi vitae suscipit. Sed ullamcorper morbi tincidunt ornare massa eget. Commodo quis imperdiet massa tincidunt nunc pulvinar. Interdum varius sit amet mattis vulputate. Pulvinar sapien et ligula ullamcorper malesuada proin. Ante in nibh mauris cursus. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Morbi tristique senectus et netus et malesuada fames ac. Lectus arcu bibendum at varius vel. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Feugiat pretium nibh ipsum consequat nisl. Sed cras ornare arcu dui vivamus arcu felis bibendum. Turpis in eu mi bibendum. Est pellentesque elit ullamcorper dignissim cras. Convallis a cras semper auctor. Donec ultrices tincidunt arcu non sodales.Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Sed tempus urna et pharetra pharetra massa massa ultricies mi. Magna eget est lorem ipsum. Parturient montes nascetur ridiculus mus mauris. Ultrices tincidunt arcu non sodales neque sodales ut etiam sit. Magna sit amet purus gravida quis blandit turpis cursus. Scelerisque in dictum non consectetur a erat nam. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque. Tincidunt vitae semper quis lectus. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim. Purus viverra accumsan in nisl nisi.";
        this.preparation = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nullam vehicula ipsum a arcu cursus. Amet volutpat consequat mauris nunc congue nisi vitae. Mauris sit amet massa vitae. Pellentesque elit ullamcorper dignissim cras. Lectus urna duis convallis convallis tellus id interdum velit. Vestibulum lectus mauris ultrices eros in cursus turpis. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada nunc vel risus commodo viverra. Dolor sed viverra ipsum nunc aliquet bibendum enim. Dui ut ornare lectus sit amet est. Arcu dui vivamus arcu felis.Aenean euismod elementum nisi quis eleifend quam adipiscing. Nisi porta lorem mollis aliquam ut porttitor. Convallis aenean et tortor at risus viverra adipiscing. Turpis in eu mi bibendum. In hendrerit gravida rutrum quisque non. Faucibus nisl tincidunt eget nullam non nisi est sit. Quis viverra nibh cras pulvinar mattis. Maecenas sed enim ut sem viverra. Fermentum et sollicitudin ac orci. Iaculis nunc sed augue lacus viverra. Tempus egestas sed sed risus. Amet tellus cras adipiscing enim eu turpis egestas pretium. Rhoncus mattis rhoncus urna neque. Sem et tortor consequat id porta nibh venenatis cras. Et magnis dis parturient montes nascetur. Lectus quam id leo in vitae turpis massa sed. Volutpat est velit egestas dui id ornare arcu. Purus sit amet volutpat consequat mauris nunc congue nisi. In cursus turpis massa tincidunt. Tellus in hac habitasse platea dictumst vestibulum rhoncus. Placerat in egestas erat imperdiet. Dolor sit amet consectetur adipiscing elit duis tristique. Non quam lacus suspendisse faucibus. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Et netus et malesuada fames ac. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque habitant. Quam elementum pulvinar etiam non quam lacus suspendisse. Interdum velit euismod in pellentesque massa. Turpis tincidunt id aliquet risus feugiat in ante.Netus et malesuada fames ac turpis egestas integer. Volutpat consequat mauris nunc congue nisi vitae suscipit. Sed ullamcorper morbi tincidunt ornare massa eget. Commodo quis imperdiet massa tincidunt nunc pulvinar. Interdum varius sit amet mattis vulputate. Pulvinar sapien et ligula ullamcorper malesuada proin. Ante in nibh mauris cursus. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Morbi tristique senectus et netus et malesuada fames ac. Lectus arcu bibendum at varius vel. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Feugiat pretium nibh ipsum consequat nisl. Sed cras ornare arcu dui vivamus arcu felis bibendum. Turpis in eu mi bibendum. Est pellentesque elit ullamcorper dignissim cras. Convallis a cras semper auctor. Donec ultrices tincidunt arcu non sodales.Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Sed tempus urna et pharetra pharetra massa massa ultricies mi. Magna eget est lorem ipsum. Parturient montes nascetur ridiculus mus mauris. Ultrices tincidunt arcu non sodales neque sodales ut etiam sit. Magna sit amet purus gravida quis blandit turpis cursus. Scelerisque in dictum non consectetur a erat nam. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque. Tincidunt vitae semper quis lectus. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim. Purus viverra accumsan in nisl nisi.";
        this.kcal = 321;
        this.likes = 32;
        this.comments = new ArrayList<>();
        this.comments.add(new Comment("Hmmm mama la pizza est buena", new User("George", "Butavent", Gender.HOMME, 23, 180, "bg et fier de l'être george aime la vie ", Diet.VEGETARIAN, 10.0, 7.0, R.drawable.bob)));
        this.comments.add(new Comment("Un pepene un pepite, un pepito", new User("Paul", "Delafuerza", Gender.HOMME, 46, 156, "bg et fier de l'être george aime la vie ", Diet.VEGETARIAN, 10.0, 2.0, R.drawable.bob)));
        this.authorName = "Pierre";
        this.eatIt = 0;
    }

    public MealLunch(String name, Bitmap picture, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) {
        this.name = name;
        this.pictureBitmap = picture;
        this.preparationTime = preparationTime;
        this.nbOfPeople = nbOfPeople;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.kcal = kcal;
        this.likes = 0;
        this.comments = new ArrayList<>();
        this.authorName = author;
    }

    public MealLunch(String name, String imageLink, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author) {
        this.name = name;
        this.imageLink = imageLink;
        this.preparationTime = preparationTime;
        this.nbOfPeople = nbOfPeople;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.kcal = kcal;
        this.likes = 0;
        this.comments = new ArrayList<>();
        this.authorName = author;
    }

    protected MealLunch(Parcel in) {
        name = in.readString();
        picture = in.readInt();
    }

    //NE PAS ENLEVER CE CONSTRUCTEUR : IMPORTANT POUR FIREBASE
    public MealLunch() {

    }

    public static List<String> validate(MealLunch meal) {
        // fields to validate : String name, int picture, int preparationTime, int nbOfPeople, String ingredients, String preparation, int kcal, String author
        List<String> errors = new ArrayList<>();
        String cleanedName = meal.getName().replaceAll("[^a-zA-Z]+", "");

        if (cleanedName.isEmpty()) {
            errors.add("Le nom de la recette est incorrect");
        }
        if (meal.getName().isEmpty()) {
            errors.add("Le nom est requis");
        }
        if (meal.getPreparationTime() == 0) {
            errors.add("Temps de préparation est requis");
        }
        if (meal.getNbOfPeople() == 0) {
            errors.add("Nombre de personnes est requis");
        }
        if (meal.getIngredients().isEmpty()) {
            errors.add("Les ingrédients sont requis");
        }
        if (meal.getPreparation().isEmpty()) {
            errors.add("La préparation est requise");
        }
        if (meal.getKcal() == 0) {
            errors.add("Les calories sont requises");
        }
        if (meal.getPictureBitmap() == null) {
            errors.add("La photo est requise");
        }
        return errors;
    }

    public void increaseEatIt() {
        this.eatIt++;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        this.likes--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getNbOfPeople() {
        return nbOfPeople;
    }

    public void setNbOfPeople(int nbOfPeople) {
        this.nbOfPeople = nbOfPeople;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getEatIt() {
        return eatIt;
    }

    public void setEatIt(int eatIt) {
        this.eatIt = eatIt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", picture=" + picture +
                ", preparationTime=" + preparationTime +
                ", nbOfPeople=" + nbOfPeople +
                ", ingredients='" + ingredients + '\'' +
                ", preparation='" + preparation + '\'' +
                ", kcal=" + kcal +
                ", eatIt=" + eatIt +
                ", likes=" + likes +
                ", comments=" + comments +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public Bitmap getPictureBitmap() {
        return pictureBitmap;
    }

    public void setPictureBitmap(Bitmap pictureBitmap) {
        this.pictureBitmap = pictureBitmap;
    }
}
