package quotes;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Quotes {
    private String [] tags;
    private String author;
    private String likes;
    private String text;

    public Quotes(String[] tags, String author, String likes, String text) {
        this.tags = tags;
        this.author = author;
        this.likes = likes;
        this.text = text;
    }
    public  static Quotes readQuotes(String path) {
        Quotes[] quotes;
        Quotes randomQuote;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {  //path="app/src/main/resources/recentquotes.json"
            Gson gson = new Gson();
            quotes = gson.fromJson(reader, Quotes[].class);
            Random random = new Random();
            randomQuote = quotes[random.nextInt(quotes.length)];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return randomQuote;
    }

    @Override
    public String toString() {
        return "Quotes{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
