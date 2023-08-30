package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class Quotes {
    private String [] tags;
    private String author;
    private String likes;
    private String text;
    private Quote quote;

    public Quotes(String[] tags, String author, String likes, String text) {
        this.tags = tags;
        this.author = author;
        this.likes = likes;
        this.text = text;
    }
    public  static Quotes readQuotesFromFile(String path) {
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
    public  static Quotes readQuotesFromApi() {
        Quotes myQuote;
        URL apiUrl=null;
        try  {
           apiUrl=new URL("https://favqs.com/api/qotd");
            HttpURLConnection apiUrlConnection = (HttpURLConnection) apiUrl.openConnection();
            apiUrlConnection.setRequestMethod("GET");
            InputStreamReader streamReader = new InputStreamReader(apiUrlConnection.getInputStream());
            BufferedReader apiBufferedReader= new BufferedReader(streamReader);
            String quoteData=apiBufferedReader.readLine();
            System.out.println(quoteData);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
           myQuote= gson.fromJson(quoteData, Quotes.class);
           File myFile = new File("app/src/test/resources/recentquotes.json");
           try (FileWriter write = new FileWriter(myFile)){
               gson.toJson(myQuote,write);
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return myQuote;
    }
    @Override
    public String toString() {
        return "Quotes{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
