package me.sfalcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StockTest
        extends TestCase
{

    static final Stock stock = new Stock(5, 100);
    static final Stock zeroStock = new Stock();

    public StockTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( StockTest.class );
    }

    public void testDividend() {
        assertEquals( zeroStock.dividendYield(), 0.0);
        assertEquals( stock.dividendYield(), 0.05 );
    }

    public void testPriceEarningRatio(){
        assertEquals( zeroStock.priceEarningRatio(), 0.0);
        assertEquals( stock.priceEarningRatio(), 20.0);
    }
}