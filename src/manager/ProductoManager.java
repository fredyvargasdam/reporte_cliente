/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import exceptions.ErrorServerException;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;
import modelo.Producto;

/**
 *
 * @author 2dam
 */
public interface ProductoManager {

    public Collection<Producto> findAllRopa() throws ClientErrorException;

    public void edit(Producto producto) throws ErrorServerException;

    public Collection<Producto> findAllProductosAsc() throws ErrorServerException;

    public Producto find(Producto producto,String id) throws ClientErrorException;

    public void create(Producto producto) throws ErrorServerException;

    public Collection<Producto> findAllProductosDesc() throws ClientErrorException;

    public Collection<Producto> findAllZapatillas() throws ClientErrorException;

    public void remove(String id) throws ErrorServerException;

    public void close();

}
