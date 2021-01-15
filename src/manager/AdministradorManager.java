/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import modelo.Administrador;
import modelo.Proveedor;

/**
 *
 * @author 2dam
 */
public interface AdministradorManager {

    public <T> T getVendedores(Class<T> responseType) throws ClientErrorException;

    public void edit(Object requestEntity) throws ClientErrorException;

    public List <Proveedor> getProveedores(GenericType responseType) throws ClientErrorException;

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException;

    public void create(Object requestEntity) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public void close();

}
