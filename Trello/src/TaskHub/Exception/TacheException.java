package TaskHub.Exception;

public class TacheException extends Exception{

    /**
     * Constructeur de TacheException
     * @param message message
     */
    public TacheException(String message){
        super(message);
    }
    /**
     * Constructeur de TacheException
     */
    public TacheException(){
        super();
    }
}
