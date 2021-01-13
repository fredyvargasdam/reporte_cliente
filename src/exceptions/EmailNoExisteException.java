/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public class EmailNoExisteException extends Exception{
    /**
     * Excepcion de email no encontrado
     */
    public EmailNoExisteException() {
        super("Email no encontrado");
    }
    
 
}
