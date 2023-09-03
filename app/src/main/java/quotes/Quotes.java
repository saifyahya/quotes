package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    protected ArrayList<Quotes> quotesData;

    public Quotes() {
        quotesData= new ArrayList<>();
    }

    public  Quotes readQuotesFromFile(String path) {
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

    public  Quotes readQuotesFromApi() {
        Quotes myQuote = null;
        URL apiUrl = null;
        try {
            Gson gson = new Gson();
            apiUrl = new URL("https://favqs.com/api/qotd");
            HttpURLConnection apiUrlConnection = (HttpURLConnection) apiUrl.openConnection();
            apiUrlConnection.setRequestMethod("GET");

            InputStreamReader streamReader = new InputStreamReader(apiUrlConnection.getInputStream());
            BufferedReader apiBufferedReader = new BufferedReader(streamReader);
            String quoteData = apiBufferedReader.readLine();

            System.out.println(quoteData);
            gson = new GsonBuilder().setPrettyPrinting().create();
            myQuote = gson.fromJson(quoteData, Quotes.class);
            System.out.println(myQuote);

            // Append the new quote to the original data
            quotesData.add(myQuote);
            System.out.println(quotesData.size());

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            myQuote = readQuotesFromFile("app/src/main/resources/recentQuotes.json"); // Read from the file
            System.out.println("Error: " + e);
        }
        // Write the updated list back to the JSON file
        WriteToFile();
        return myQuote;
    }
    public void  WriteToFile() {
        try(FileWriter writer = new FileWriter(new File("app/src/main/resources/recentQuotes1.json"))) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(quotesData, writer);

        } catch (IOException e) {
e.printStackTrace();        }
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