/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

public class App {

    public static void main(String[] args) {
       /*Quotes myQuote= Quotes.readQuotesFromFile("app/src/test/resources/recentquotes.json");
        System.out.println(myQuote.toString());*/

        Quotes myApiQuote= Quotes.readQuotesFromApi();
        System.out.println(myApiQuote.toString());
    }
}
