/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import exceptions.AutenticacionFallidaException;
import exceptions.ErrorEnviarEmailException;
import exceptions.ErrorServerException;
import exceptions.UsuarioNoEncontradoException;
import javax.ws.rs.ClientErrorException;
import modelo.Usuario;

/**
 *
 * @author Fredy
 */
public interface UsuarioManager {

    public void edit(Usuario usuario ) throws ClientErrorException;

    public Usuario find(String id) throws ClientErrorException;

    public Usuario usuarioByLogin( String login, String pass) throws AutenticacionFallidaException;

   public Usuario enviarMensajeEmail( String email, String pass) throws ErrorEnviarEmailException;

    public Usuario usuarioLogin( String login) throws UsuarioNoEncontradoException,ErrorServerException;

    public void create(Usuario usuario) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public void close();

}
