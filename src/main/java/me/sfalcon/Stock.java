package me.sfalcon;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class Stock {

    private double lastDividend = 0;
    private double tickerPrice = 0;

    public Stock (){}

    public Stock (double lastDividend, double tickerPrice){
        this.lastDividend = lastDividend;
        this.tickerPrice = tickerPrice;
    }

    public double dividendYield(){
        if (tickerPrice == 0) return tickerPrice;
        return lastDividend/tickerPrice;
    }

}
