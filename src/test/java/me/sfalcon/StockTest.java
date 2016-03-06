package me.sfalcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static me.sfalcon.Indicator.*;


public class StockTest
        extends TestCase
{

    static Stock stock = null;
    static final Stock zeroStock = new Stock();
    UnaryOperator<Double> round = (d) -> {
        double val = d*100;
        return  Math.ceil(val)/100;
    };

    public StockTest( String testName )
    {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite(StockTest.class);
    }

    public void testDividend() {
        stock = new Stock(5, 100);
        assertEquals( 0.0, zeroStock.dividendYield());
        assertEquals( 0.05, stock.dividendYield());
    }

    public void testPriceEarningRatio(){
        stock = new Stock(5, 100);
        assertEquals( 0.0, zeroStock.priceEarningRatio());
        assertEquals( 20.0, stock.priceEarningRatio());
    }

    public void testTrade(){
        stock = new Stock(5, 100);
        //can add trades
        Trade trade = new Trade(ZonedDateTime.now(), 1, BUY, 125);
        stock.trade(trade);

        //dividend and priceEarning change because so does tickerPrice
        assertEquals(0.04, stock.dividendYield());
        assertEquals(25.0, stock.priceEarningRatio());
    }

    public void testPrice(){
        stock = new Stock(5, 100);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime fiveMinAgo = now.minus(5, ChronoUnit.MINUTES);
        ZonedDateTime tenMinAgo = now.minus(10, ChronoUnit.MINUTES);
        ZonedDateTime twentyMinAgo = now.minus(20, ChronoUnit.MINUTES);

        stock.trade(new Trade(twentyMinAgo, 3, SELL, 110));
        stock.trade(new Trade(tenMinAgo, 2, BUY, 105));
        stock.trade(new Trade(fiveMinAgo, 1, SELL, 100));
        stock.trade(new Trade(now, 4, BUY, 98));

        assertEquals(100.29, round.apply(stock.price()));
    }

    public void testGeometricMean(){
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(8, 100));
        stocks.add(new Stock(23, 60));
        stocks.add(new Stock(11, 100));
        stocks.add(new Stock(13, 250));

        assertEquals(110.67, round.apply(Stock.geometricMean(stocks)));
    }

}