# FakeExchange
An assignment I did for a job interview. Buys and sells a random number of 'stocks' from an 'exchange'.

All the code is in src. Exchange has the csv and txt file used for the 'stock market' and logging system.

NOTE: In `CSVReader.java` and `Logger.java` both reference the files in Exchange as 'classes/exchange/'.
This is due to Maven building in a weird way. If you're debugging, remove the 'classes' bit. But remember to put it back when building!

A freshly made build is available in target. Type the following in command line pointing to the folder:


`java −cp Assignment-1.0-jar-with-dependencies.jar au.com.livewirelabs.assignment.Assignment −exchange ASX`

Where '-exchange' starts the process, and 'ASX' is any 3 Stock Market. Check the csv file for actual names, and make your own! The program does nothing if the stock market doesn't exist in the exchange.

This took about 4 hours total, and was also an exercise in understanding [Google Guice.](https://github.com/google/guice)