package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import fr.univ.cotedazur.polytech.projet_td2_regime.profile.User;

public class Comment {
    private String text;
    private User author;

    // Public empty constructor for Firebase
    public Comment() {
    }

    public Comment(String text, User author) {
        this.text = text;
        this.author = author;
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

}
