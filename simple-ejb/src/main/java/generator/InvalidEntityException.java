package generator;


import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;

/**
 * Created by maks on 11.05.15.
 */
public class InvalidEntityException extends EJBTransactionRolledbackException{


    public InvalidEntityException () {
        super("Entity class have some invalid fields");
    }

    public InvalidEntityException (String message) {
        super(message);
    }

    public InvalidEntityException (Exception cause) {
        super("Entity class have some invalid fields", cause);
    }

    public InvalidEntityException (String message, Exception cause) {
        super(message, cause);
    }


}
