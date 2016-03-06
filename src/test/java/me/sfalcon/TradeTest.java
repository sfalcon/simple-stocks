package me.sfalcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static me.sfalcon.Indicator.*;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class TradeTest
        extends TestCase {

    public TradeTest( String testName )
    {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite(TradeTest.class);
    }

    public void testConstructor(){
        Trade trade = new Trade();

        //cant construct or set negative or zero shares/price
        ExceptionAssertion negatives = new ExceptionAssertion(() -> new Trade(ZonedDateTime.now(), -1, BUY, 125));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));
        negatives = new ExceptionAssertion(() -> new Trade(ZonedDateTime.now(), 0, BUY, 125));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));

        negatives = new ExceptionAssertion(() -> new Trade(ZonedDateTime.now(), 1, SELL, -125));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));
        negatives = new ExceptionAssertion(() -> new Trade(ZonedDateTime.now(), 1, SELL, 0));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));

        negatives = new ExceptionAssertion(() -> trade.setShares(-1));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));
        negatives = new ExceptionAssertion(() -> trade.setShares(0));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));

        negatives = new ExceptionAssertion(()-> trade.setPrice(-125));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));
        negatives = new ExceptionAssertion(()-> trade.setPrice(0));
        assertTrue(negatives.hasThrownError(IllegalArgumentException.class));
    }

    public void testMadeWithinLast15min(){
        Trade trade = new Trade();
        trade.setTimestamp(ZonedDateTime.now().minus(5, ChronoUnit.MINUTES));
        assertTrue(trade.madeWithinLast15Min());
        trade.setTimestamp(ZonedDateTime.now().minus(15, ChronoUnit.MINUTES));
        assertFalse(trade.madeWithinLast15Min());
    }

}
