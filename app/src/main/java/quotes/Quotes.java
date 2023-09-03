package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Quotes {
    private String author;
    private String text;
    private Quote quote;
    private static ArrayList<Quotes> quotesData;

    public Quotes(String author, String text) {
        this.author = author;
        this.text = text;
        quotesData= new ArrayList<>();
    }

    public static Quotes readQuotesFromFile(String path) {

        Quotes randomQuote = null;
        Quotes[]quotes;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new Gson();
           quotes=gson.fromJson(reader, Quotes[].class);
           quotesData.addAll( Arrays.asList(quotes));
            Random random = new Random();
            randomQuote = quotesData.get(random.nextInt(quotesData.size()));
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
            apiUrl = new URL("https://favqscom/api/qotd");
            HttpURLConnection apiUrlConnection = (HttpURLConnection) apiUrl.openConnection();
            apiUrlConnection.setRequestMethod("GET");
            InputStreamReader streamReader = null;
                streamReader = new InputStreamReader(apiUrlConnection.getInputStream());
                BufferedReader apiBufferedReader = new BufferedReader(streamReader);
                String quoteData = apiBufferedReader.readLine();

                System.out.println(quoteData);
                gson = new GsonBuilder().setPrettyPrinting().create();
                myQuote = gson.fromJson(quoteData, Quotes.class);
                quotesData.add(myQuote);   //appending the new quote to the original list
            System.out.println(myQuote);
            File myFile = new File("app/src/main/resources/recentQuotes.json");
            try (FileWriter write = new FileWriter(myFile)) {
                gson.toJson(quoteData, write);          // writing the new list to json file
            }
        } catch (IllegalStateException | IOException e) {
            myQuote = readQuotesFromFile("app/src/main/resources/recentQuotes.json");  //read from the file
            System.out.println("Error"+e);
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