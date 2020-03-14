package koalatree.exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(long id){
        super("Could not find transaction " + id);
    }
}
