package me.sfalcon;

/**
 * Created by sfalcon on 3/6/2016.
 */
public class ExceptionAssertion {

    private Throwable caught;

    public ExceptionAssertion(Runnable lambda){
        try{
            lambda.run();
        }catch (Throwable caught) {
            this.caught = caught;
        }
    }

    public boolean hasThrownError(Class<? extends Throwable> exceptionClass){
        assert(caught!=null) : "The expression didn't throw any error";
        return this.caught.getClass() == exceptionClass;
    }
}
