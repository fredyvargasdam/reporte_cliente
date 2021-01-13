package exceptions;
/**
 *
 * @author Lorena Cáceres Manuel
 */
public class UsuarioNoEncontradoException extends Exception {
    /**
     * Excepcion de usuario introducido no encontrado
     */
    public UsuarioNoEncontradoException(){
        super("El usuario introducido no se ha encontrado");
    }
}
