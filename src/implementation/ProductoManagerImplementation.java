/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.ProductoRESTClient;
import exceptions.ErrorServerException;
import exceptions.ProductoExistenteException;
import java.net.ConnectException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import manager.ProductoManager;
import modelo.Producto;

/**
 *
 * @author Fredy
 */
public class ProductoManagerImplementation implements ProductoManager {

    private ProductoRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("ProductoManagerImplementation");

    public ProductoManagerImplementation() {
        webClient = new ProductoRESTClient();
    }

    
    public Collection<Producto> findAllRopa() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void edit(Producto producto) throws ErrorServerException {
        try {
            webClient.edit(producto);
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                LOGGER.severe("ProductoManagerImplementation: edit " + e.getMessage());
                throw new ErrorServerException();
            }
        }
    }

    
    public Collection<Producto> findAllProductosAsc() throws ClientErrorException {
        List<Producto> productos = null;
        try {
            productos = webClient.findAllProductosAsc(new GenericType<List<Producto>>() {
            });

        } catch (Exception e) {
            LOGGER.severe("findAllProductosAsc:" + e.getMessage());
        }
        return productos;
    }

    
    public Producto find(Producto producto, String id) throws ClientErrorException {
        producto = null;
        try {
            producto = webClient.find(Producto.class, id);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE, "ClientErrorException");
        }
        return producto;
    }

    
    public void create(Producto producto) throws ErrorServerException {
        try {
            webClient.create(producto);
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                LOGGER.severe("ProductoManagerImplementation: create " + e.getMessage());
                throw new ErrorServerException();
            }
        }
    }

    
    public Collection<Producto> findAllProductosDesc() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Collection<Producto> findAllZapatillas() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void remove(String id) throws ClientErrorException, ErrorServerException {
        try {
            webClient.remove(id);
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                LOGGER.severe("ProductoManagerImplementation: remove " + e.getMessage());
                throw new ErrorServerException();
            }
        }
    }

    
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
