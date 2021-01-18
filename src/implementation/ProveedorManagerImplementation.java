/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.ProveedorRESTClient;
import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import exceptions.InsertException;
import exceptions.ProductoNotFoundException;
import exceptions.ProveedorNotFoundException;
import exceptions.ProveedorYaExisteException;
import exceptions.SelectException;
import exceptions.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import manager.ProveedorManager;
import modelo.Proveedor;

/**
 *
 * @author Lorena
 */
public class ProveedorManagerImplementation implements ProveedorManager {
    
    private static final Logger LOG = Logger.getLogger(ProveedorManagerImplementation.class.getName());
    
    private ProveedorRESTClient webClient;
    
    @Override
    public void remove(String id) throws ClientErrorException {
        try {
            webClient = new ProveedorRESTClient();
            webClient.remove(id);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "ClientErrorException");
        }
    }
    
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void edit(Proveedor proveedor) throws ClientErrorException, UpdateException, ErrorBDException, ErrorServerException, ProveedorNotFoundException {
      webClient = new ProveedorRESTClient();
      webClient.edit(proveedor);
    }
    
    @Override
    public Proveedor find(Proveedor proveedor, String id) throws ClientErrorException, SelectException, ProveedorNotFoundException, ErrorBDException, ErrorServerException {
        proveedor = null;
        try {
            proveedor = webClient.find(Proveedor.class, id);
        } catch (Exception e) {
            LOG.log(Level.INFO, "");
        }
        
        return proveedor;
    }
    
    @Override
    public Proveedor getProductos(Proveedor proveedor, String id) throws ErrorBDException, ErrorServerException, ProductoNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void create(Proveedor proveedor) throws ClientErrorException, InsertException, ProveedorYaExisteException, ErrorBDException, ErrorServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
