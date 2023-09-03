/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
  @Test void testQuotesFromFile() {  // test can read from Json file and choose a random quote
      Quotes quoteInstance = new Quotes();
Quotes result = quoteInstance.readQuotesFromFile("src/test/resources/recentQuotes.json") ;
      Assertions.assertNotNull(result);
  }
    @Test void testQuotesFromFileFailed() {  // provide not correct path
        Quotes quoteInstance = new Quotes();
        Quotes result = quoteInstance.readQuotesFromFile("saif/src/test/resources/recentQuotes.json") ;
        Assertions.assertNull(result);
    }
    @Test void testQuotesFromApi() {  // test can read from api
        Quotes quoteInstance = new Quotes();
        Quotes result = quoteInstance.readQuotesFromApi();
        Assertions.assertNotNull(result);
    }

}
