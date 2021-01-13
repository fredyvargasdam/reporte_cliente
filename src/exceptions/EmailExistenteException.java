package exceptions;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public class EmailExistenteException extends Exception {
    /**
     * Excepcion de email en uso
     */
    public  EmailExistenteException(){
    super("Email en uso");
}
}
