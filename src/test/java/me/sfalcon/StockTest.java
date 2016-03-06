package me.sfalcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static me.sfalcon.Indicator.*;


public class StockTest
        extends TestCase
{

    static Stock stock = null;
    static final Stock zeroStock = new Stock();

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

        Trade first = new Trade(now, 3, BUY, 300);
    }

}