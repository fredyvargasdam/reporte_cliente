/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import javax.ws.rs.ClientErrorException;

/**
 *
 * @author 2dam
 */
public interface UsuarioManager {

    public void edit(Object requestEntity) throws ClientErrorException;

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException;

    public <T> T usuarioByLogin(Class<T> responseType, String login, String pass) throws ClientErrorException;

    public void enviarMensajeEmail(Object requestEntity) throws ClientErrorException;

    public <T> T usuarioLogin(Class<T> responseType, String login) throws ClientErrorException;

    public void create(Object requestEntity) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public void close();

}
