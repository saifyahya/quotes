package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class Quotes {
    private String author;
    private String text;
    private Quote quote;

    public Quotes(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public static Quotes readQuotesFromFile(String path) {
        Quotes[] quotes;
        Quotes randomQuote = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {  //path="app/src/test/resources/recentQuotes.json"
            Gson gson = new Gson();
            quotes = gson.fromJson(reader, Quotes[].class);
            Random random = new Random();
            randomQuote = quotes[random.nextInt(quotes.length)];
            System.out.println(randomQuote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomQuote;
    }

    public static Quotes readQuotesFromApi() {
        Quotes myQuote = null;
        URL apiUrl = null;
        try {
            Gson gson = new Gson();
            apiUrl = new URL("https://example.com");
            HttpURLConnection apiUrlConnection = (HttpURLConnection) apiUrl.openConnection();
            apiUrlConnection.setRequestMethod("GET");
            InputStreamReader streamReader = null;
            if (apiUrlConnection.getResponseCode() > 299) {    // indicates a failed response
                myQuote = readQuotesFromFile("app/src/test/resources/recentQuotes.json");  //read from the file
            } else {
                streamReader = new InputStreamReader(apiUrlConnection.getInputStream());
                BufferedReader apiBufferedReader = new BufferedReader(streamReader);
                String quoteData = apiBufferedReader.readLine();
                System.out.println(quoteData);
                gson = new GsonBuilder().setPrettyPrinting().create();
                myQuote = gson.fromJson(quoteData, Quotes.class);
            }
            File myFile = new File("app/src/test/resources/recentQuotesFromApi.json");
            try (FileWriter write = new FileWriter(myFile)) {
                gson.toJson(myQuote, write);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myQuote;
    }


    @Override
    public String toString() {
        if (author == null) //  read throw the api
            return "Quotes{" +
                     quote +
                    '}';
        else  // read throw file
            return "Quotes{" +
                    "author='" + author + '\'' +
                    ", text='" + text + '\'' +
                    '}';
    }
}