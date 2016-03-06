package me.sfalcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StockTest
        extends TestCase
{

    static final Stock stock = new Stock(5, 100);

    public StockTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( StockTest.class );
    }

    public void testDividend()
    {

        assertEquals( stock.dividendYield(), 0.05 );
    }
}