/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
  @Test void testQuotesFromFile() {  // test can read from Json file and choose a random quote
Quotes quote = Quotes.readQuotesFromFile("src/test/resources/recentQuotes.json") ;
      Assertions.assertNotNull(quote);
  }
    @Test void testQuotesFromFileFailed() {  // provide not correct path
        Quotes quote = Quotes.readQuotesFromFile("saif/src/test/resources/recentQuotes.json") ;
        Assertions.assertNull(quote);
    }
    @Test void testQuotesFromApi() {  // test can read from api
        Quotes quote = Quotes.readQuotesFromApi();
        Assertions.assertNotNull(quote);
    }

}
