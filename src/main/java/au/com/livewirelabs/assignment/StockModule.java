package au.com.livewirelabs.assignment;

import com.google.inject.AbstractModule;

/**
 * Module for Guice to read
 */
public class StockModule extends AbstractModule {


    @Override
    protected void configure() {

        bind(StockExchange.class).annotatedWith(FakeStock.class).to(FakeStockExchange.class);
        bind(StockExchange.class).annotatedWith(NotRealStock.class).to(NotRealStockExchange.class);

    }

}