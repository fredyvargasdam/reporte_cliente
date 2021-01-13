package exceptions;
/**
 *
 * @author Lorena Cáceres Manuel
 */
public class UsuarioExistenteException extends Exception {
    /**
     * Excepcion de usuario ya dado de alta
     */
    public UsuarioExistenteException (){
        super("Usuario ya dado de alta");
    }
}
