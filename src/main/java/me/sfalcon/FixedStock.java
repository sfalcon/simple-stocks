package me.sfalcon;

/**
 * Created by samue on 3/6/2016.
 */
public class FixedStock extends Stock{

    private double fixedDividend;

    public FixedStock(double lastDividend, double fixedDividend, double parValue){
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.tickerPrice = parValue; //assuming tickerPrice is parValue when defined
    }

    @Override
    public double dividendYield() {
        return (fixedDividend*parValue)/tickerPrice;
    }
}
