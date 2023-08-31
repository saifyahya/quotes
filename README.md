## readQuotes
1. readQuotesFromFile
It is a function that reads from a given json file path, and parse the content of the file into objects of type Quotes class with the following attributes:
1. String author;
2. String text;
- all objects are stored into array of type Quotes, then the function choose one object randomly from the array.
- toString method is used to show the author and text of each quote.

2. readQuotesFromApi
It is a function that read a random quote from Api, and parse the content of the file into objects of type Quotes class within object of type Quote, which is another class contains: author and body.
- if the Http request failed the function will read a quote from the local file.
- when the function reads a quote from Api, it is written inside a new file. 
