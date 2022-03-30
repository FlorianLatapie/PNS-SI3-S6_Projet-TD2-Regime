package fr.univ.cotedazur.polytech.projet_td2_regime.home;

import android.os.Parcel;

public class Meal {
    private String name;
    private float price;
    private int picture;
    private int preparationTime;
    private int nbOfPeople;
    private String ingredients;
    private String preparation;
    private int kcal;
    private int eatIt;
    private int likes;
    private int comments;
    private String author;


    public Meal(String name, float price, int picture) {
        this.name = name;
        this.price = price;
        this.picture =picture;
        this.ingredients = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nullam vehicula ipsum a arcu cursus. Amet volutpat consequat mauris nunc congue nisi vitae. Mauris sit amet massa vitae. Pellentesque elit ullamcorper dignissim cras. Lectus urna duis convallis convallis tellus id interdum velit. Vestibulum lectus mauris ultrices eros in cursus turpis. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada nunc vel risus commodo viverra. Dolor sed viverra ipsum nunc aliquet bibendum enim. Dui ut ornare lectus sit amet est. Arcu dui vivamus arcu felis.Aenean euismod elementum nisi quis eleifend quam adipiscing. Nisi porta lorem mollis aliquam ut porttitor. Convallis aenean et tortor at risus viverra adipiscing. Turpis in eu mi bibendum. In hendrerit gravida rutrum quisque non. Faucibus nisl tincidunt eget nullam non nisi est sit. Quis viverra nibh cras pulvinar mattis. Maecenas sed enim ut sem viverra. Fermentum et sollicitudin ac orci. Iaculis nunc sed augue lacus viverra. Tempus egestas sed sed risus. Amet tellus cras adipiscing enim eu turpis egestas pretium. Rhoncus mattis rhoncus urna neque. Sem et tortor consequat id porta nibh venenatis cras. Et magnis dis parturient montes nascetur. Lectus quam id leo in vitae turpis massa sed. Volutpat est velit egestas dui id ornare arcu. Purus sit amet volutpat consequat mauris nunc congue nisi. In cursus turpis massa tincidunt. Tellus in hac habitasse platea dictumst vestibulum rhoncus. Placerat in egestas erat imperdiet. Dolor sit amet consectetur adipiscing elit duis tristique. Non quam lacus suspendisse faucibus. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Et netus et malesuada fames ac. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque habitant. Quam elementum pulvinar etiam non quam lacus suspendisse. Interdum velit euismod in pellentesque massa. Turpis tincidunt id aliquet risus feugiat in ante.Netus et malesuada fames ac turpis egestas integer. Volutpat consequat mauris nunc congue nisi vitae suscipit. Sed ullamcorper morbi tincidunt ornare massa eget. Commodo quis imperdiet massa tincidunt nunc pulvinar. Interdum varius sit amet mattis vulputate. Pulvinar sapien et ligula ullamcorper malesuada proin. Ante in nibh mauris cursus. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Morbi tristique senectus et netus et malesuada fames ac. Lectus arcu bibendum at varius vel. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Feugiat pretium nibh ipsum consequat nisl. Sed cras ornare arcu dui vivamus arcu felis bibendum. Turpis in eu mi bibendum. Est pellentesque elit ullamcorper dignissim cras. Convallis a cras semper auctor. Donec ultrices tincidunt arcu non sodales.Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Sed tempus urna et pharetra pharetra massa massa ultricies mi. Magna eget est lorem ipsum. Parturient montes nascetur ridiculus mus mauris. Ultrices tincidunt arcu non sodales neque sodales ut etiam sit. Magna sit amet purus gravida quis blandit turpis cursus. Scelerisque in dictum non consectetur a erat nam. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque. Tincidunt vitae semper quis lectus. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim. Purus viverra accumsan in nisl nisi.";
        this.preparation = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nullam vehicula ipsum a arcu cursus. Amet volutpat consequat mauris nunc congue nisi vitae. Mauris sit amet massa vitae. Pellentesque elit ullamcorper dignissim cras. Lectus urna duis convallis convallis tellus id interdum velit. Vestibulum lectus mauris ultrices eros in cursus turpis. Eu mi bibendum neque egestas congue quisque egestas diam in. Malesuada nunc vel risus commodo viverra. Dolor sed viverra ipsum nunc aliquet bibendum enim. Dui ut ornare lectus sit amet est. Arcu dui vivamus arcu felis.Aenean euismod elementum nisi quis eleifend quam adipiscing. Nisi porta lorem mollis aliquam ut porttitor. Convallis aenean et tortor at risus viverra adipiscing. Turpis in eu mi bibendum. In hendrerit gravida rutrum quisque non. Faucibus nisl tincidunt eget nullam non nisi est sit. Quis viverra nibh cras pulvinar mattis. Maecenas sed enim ut sem viverra. Fermentum et sollicitudin ac orci. Iaculis nunc sed augue lacus viverra. Tempus egestas sed sed risus. Amet tellus cras adipiscing enim eu turpis egestas pretium. Rhoncus mattis rhoncus urna neque. Sem et tortor consequat id porta nibh venenatis cras. Et magnis dis parturient montes nascetur. Lectus quam id leo in vitae turpis massa sed. Volutpat est velit egestas dui id ornare arcu. Purus sit amet volutpat consequat mauris nunc congue nisi. In cursus turpis massa tincidunt. Tellus in hac habitasse platea dictumst vestibulum rhoncus. Placerat in egestas erat imperdiet. Dolor sit amet consectetur adipiscing elit duis tristique. Non quam lacus suspendisse faucibus. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Et netus et malesuada fames ac. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque habitant. Quam elementum pulvinar etiam non quam lacus suspendisse. Interdum velit euismod in pellentesque massa. Turpis tincidunt id aliquet risus feugiat in ante.Netus et malesuada fames ac turpis egestas integer. Volutpat consequat mauris nunc congue nisi vitae suscipit. Sed ullamcorper morbi tincidunt ornare massa eget. Commodo quis imperdiet massa tincidunt nunc pulvinar. Interdum varius sit amet mattis vulputate. Pulvinar sapien et ligula ullamcorper malesuada proin. Ante in nibh mauris cursus. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Morbi tristique senectus et netus et malesuada fames ac. Lectus arcu bibendum at varius vel. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Feugiat pretium nibh ipsum consequat nisl. Sed cras ornare arcu dui vivamus arcu felis bibendum. Turpis in eu mi bibendum. Est pellentesque elit ullamcorper dignissim cras. Convallis a cras semper auctor. Donec ultrices tincidunt arcu non sodales.Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Sed tempus urna et pharetra pharetra massa massa ultricies mi. Magna eget est lorem ipsum. Parturient montes nascetur ridiculus mus mauris. Ultrices tincidunt arcu non sodales neque sodales ut etiam sit. Magna sit amet purus gravida quis blandit turpis cursus. Scelerisque in dictum non consectetur a erat nam. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque. Tincidunt vitae semper quis lectus. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim. Purus viverra accumsan in nisl nisi.";
        this.kcal = 321;
        this.likes= 32;
        this.comments = 153;
        this.author = "Pierre";
        this.eatIt = 0;

    }

    protected Meal(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        picture = in.readInt();
    }

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getPicture(){ return picture;}
    public String getIngredients(){ return ingredients;}
    public String getPreparation(){ return preparation;}
    public int getKcal(){ return kcal;}
    public int getEatIt(){ return eatIt;}
    public int getLikes() { return likes;}
    public int getComments() { return comments;}
    public String getAuthor() { return author;}
    public int getPreparationTime() {
        return preparationTime;
    }

    public int getNbOfPeople() {
        return nbOfPeople;
    }

    public void increaseEatIt(){ this.eatIt++;}
    public void increaseLikes(){ this.likes++;}


}
