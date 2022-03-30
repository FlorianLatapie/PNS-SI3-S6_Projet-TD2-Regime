package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;
import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;

public class Comment {
    private String text;
    private User author;
    private int time;

    public Comment(String text, User author, int time){
        this.text = text;
        this.author = author;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
