/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.UsuarioRESTClient;
import javax.ws.rs.ClientErrorException;
import manager.UsuarioManager;
import modelo.Usuario;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public class UsuarioManagerImplementation implements UsuarioManager {
    
    private UsuarioRESTClient webClient;
    
    @Override
    public void edit(Object requestEntity) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Usuario find(Usuario usuario, String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Usuario usuarioByLogin(Usuario usuario, String login, String pass) throws ClientErrorException {
        usuario = null;
        usuario = webClient.usuarioByLogin(Usuario.class, login, pass);
        return usuario;
    }
    
    @Override
    public void create(Usuario usuario) throws ClientErrorException {
        webClient = new UsuarioRESTClient();
        webClient.create(usuario);
    }
    
    @Override
    public void remove(String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
