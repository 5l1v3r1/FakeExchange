package au.com.livewirelabs.assignment;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The Main Class
 */
class Assignment {

    public static void main(String[] args) throws InvalidCodeException, InsufficientUnitsException {
        Injector injector = Guice.createInjector(new StockModule());
        Trade trade = injector.getInstance(Trade.class);

        //check for arguments!
        if(args.length != 0){

            for(int i=0;i<args.length;i++){
                if(args[i].equals("-exchange")){
                    if(i != args.length - 1){
                        trade.trade(args[i+1]);
                    }
                }
            }
        }
        System.exit(0);
    }
}