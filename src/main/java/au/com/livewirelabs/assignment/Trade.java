package au.com.livewirelabs.assignment;

import com.google.inject.Inject;

import java.util.Map;
import java.util.Random;

/**
 * Handles management of the stock exchange, and any trading
 */
class Trade {

    private final StockExchange stockExchange;
    //the option for a second stock exchange is here! Uses for it are commented out in this class.
    private final StockExchange otherStockExchange;

    @Inject
    Trade(@FakeStock StockExchange stockExchange, @NotRealStock StockExchange otherStockExchange){
        this.stockExchange = stockExchange;
        this.otherStockExchange = otherStockExchange;
    }


    void trade(String code) throws InvalidCodeException, InsufficientUnitsException {

        //added the max to keep things in perspective. Also conveniently avoided negative numbers!
        Integer max = 1000;
        //random buying and selling
        Random rand = new Random();
        Random mainRand = new Random();
        Integer total = mainRand.nextInt(max);
        for(int i = 0;i<total;i++){
            stockExchange.buy(code,rand.nextInt(max));
            stockExchange.sell(code,rand.nextInt(max));
            /*
            otherStockExchange.buy(code,rand.nextInt(max));
            otherStockExchange.sell(code,rand.nextInt(max));
             */
        }

        //grab the map
        Map<String,Integer> stockMap = stockExchange.getOrderBookTotalVolume();

        /*
        Map<String,Integer> stockMap = otherStockExchange.getOrderBookTotalVolume();
         */

        //print the list of stock
        System.out.println("A total of " + total + " transactions occurred.");
        System.out.println("Stock Amounts");
        if(stockMap != null){
            for (Map.Entry<String, Integer> entry : stockMap.entrySet()) {
                System.out.println("Stock code " + entry.getKey() + " has " + entry.getValue() + " stock remaining.");
            }
            CSVReader reader = new CSVReader();
            reader.saveMap(stockMap);
        } else {
            System.out.println("There are none.");
        }

        //print total cost of transactions
        System.out.println("Total cost of transactions = AU$" + stockExchange.getTradingCosts());

        /*
        System.out.println("Total cost of transactions = AU$" + otherStockExchange.getTradingCosts());
         */

    }
}
