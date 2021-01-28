/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.ClienteRESTClient;
import client.ReservaRESTClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import manager.ClienteManager;
import modelo.Cliente;
import modelo.Producto;
import modelo.Reserva;

/**
 * Implementación del cliente
 * @author Fredy
 */
public class ClienteManagerImplementation implements ClienteManager {
    
    private final ClienteRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("ClienteManagerImplementation");
    
    public ClienteManagerImplementation() {
        webClient = new ClienteRESTClient();
    }
    
    
    public void edit(Cliente cliente) throws ClientErrorException {
        try {
            webClient.edit(cliente);
        } catch (Exception e) {
            LOGGER.info("ClienteManagerImplementation::edit");
        }     
    }
    
    
    public List<Producto> findAllProductosAsc() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Cliente find(String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    public Reserva findReserva(String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void create(Object requestEntity) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void remove(String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Cliente> findCliente() throws ClientErrorException {
        List<Cliente> cliente = null;
        cliente = webClient.findCliente(new GenericType<List<Cliente>>() {
        });
        for (Cliente res : cliente) {
            LOGGER.log(Level.INFO, "Clientes: {0}", res);
        }
        return cliente;
    }
    
}