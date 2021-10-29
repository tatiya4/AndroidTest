package com.codetest.demotest.Model;

public class Animechan
{

    String anime;
    String character;
    String quote;

    public Animechan(String anime, String character, String quote) {
        this.anime = anime;
        this.character = character;
        this.quote = quote;
    }


    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
