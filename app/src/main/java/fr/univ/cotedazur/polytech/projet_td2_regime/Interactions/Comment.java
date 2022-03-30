package fr.univ.cotedazur.polytech.projet_td2_regime.Interactions;

import fr.univ.cotedazur.polytech.projet_td2_regime.profile.Account;

public class Comment {
    private String text;
    private Account author;
    private int time;

    public Comment(String text, Account author, int time){
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

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
