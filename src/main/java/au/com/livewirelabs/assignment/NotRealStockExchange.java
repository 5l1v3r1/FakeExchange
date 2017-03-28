package au.com.livewirelabs.assignment;

import org.omg.CORBA.DynAnyPackage.Invalid;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The stock exchange class. Could be made different if wanted, but currently just a copy of FakeStockExchange
 */
public class NotRealStockExchange implements StockExchange {

    private final Map<String,Integer> stockMap;
    private final Logger logger;

    private BigDecimal cost = new BigDecimal("0.0");

    NotRealStockExchange(){
        logger = new Logger();
        CSVReader reader = new CSVReader();
        this.stockMap = reader.buildMap();
    }

    //buys! Checks if the code exists, then if there's enough stock, then removes stock
    public void buy(String code, Integer units) throws InsufficientUnitsException, InvalidCodeException {
        if (stockMap != null) {
            //try for incorrect code
            try{
                if(stockMap.containsKey(code)){
                    Integer stockNo = stockMap.get(code);
                    //try for incorrect units
                    try{
                        /*
                         * NOTE: I've had random negatives show up here. Not sure why?
                         */
                        if(stockNo > units && units >= 0){
                            stockMap.put(code,stockNo - units);
                            costCheck(code);
                            logger.log("Bought " + units + " units from " + code);
                        } else {
                            throw new InsufficientUnitsException(stockNo, code, units);
                        }
                    } catch(InsufficientUnitsException ex){
                        //make more efficient later!
                        logger.log("Insufficient Units Exception: there's " + stockNo+ " units in " + code + ", cannot buy " + units + " units.");
                    }
                } else {
                    throw new InvalidCodeException(code);
                }
            } catch(InvalidCodeException ex){
                logger.log("Code Exception: " + code + " is not a valid code.");
            }

        }
    }

    //sells! Checks if the code exists, then adds stock to it
    public void sell(String code, Integer units) throws InvalidCodeException {
        if (stockMap != null) {
            //try for incorrect code
            try{
                if(stockMap.containsKey(code)){
                    Integer stockNo = stockMap.get(code);
                    stockMap.put(code,stockNo + units);
                    logger.log("Sold " + units + " units from " + code);
                    costCheck(code);

                } else {
                    throw new InvalidCodeException(code);
                }
            } catch(InvalidCodeException ex){
                logger.log("Code Exception: " + code + " is not a valid code.");
            }

        }
    }

    //gets the Stock Map
    public Map<String, Integer> getOrderBookTotalVolume() {
        return stockMap;
    }

    public BigDecimal getTradingCosts() {
        return cost;
    }

    //used to check for the fees involved with trading on the cxa/asx
    private void costCheck(String code){
        String cxa = "CXA";
        String asx = "ASX";
        if(code.equals(cxa)){
            cost = cost.add(new BigDecimal("0.05"));
        } else if(code.equals(asx)){
            cost = cost.add(new BigDecimal("0.07"));
        }
    }
}
