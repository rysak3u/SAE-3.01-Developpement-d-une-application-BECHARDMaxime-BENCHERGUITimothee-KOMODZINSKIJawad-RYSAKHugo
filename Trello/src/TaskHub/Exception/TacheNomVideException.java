package TaskHub.Exception;

public class TacheNomVideException extends TacheException{
    /**
     * Constructeur de TacheNomVideException
     * @param message message
     */
    public TacheNomVideException(String message) {
        super(message);
    }

    /**
     * Constructeur de TacheNomVideException
     */
    public TacheNomVideException(){
        super();
    }
}
