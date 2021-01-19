/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import exceptions.AutenticacionFallidaException;
import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import exceptions.SelectException;
import exceptions.UsuarioNotFoundException;
import javax.ws.rs.ClientErrorException;
import modelo.Usuario;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public interface UsuarioManager {

    public void edit(Object requestEntity) throws ClientErrorException;

    public Usuario find(Usuario usuario, String id) throws ClientErrorException;

    public Usuario usuarioByLogin(Usuario usuario, String login, String pass) throws ClientErrorException, AutenticacionFallidaException, UsuarioNotFoundException, ErrorBDException, ErrorServerException, SelectException;

    public void create(Usuario usuario) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public void close();

}
