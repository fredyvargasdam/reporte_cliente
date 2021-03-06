/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import modelo.Cliente;
import modelo.Producto;
import modelo.Reserva;

/**
 *
 * @author Fredy Vargas Flores
 */
public interface ClienteManager {

    public void edit(Cliente cliente) throws ClientErrorException;

    public List<Producto> findAllProductosAsc() throws ClientErrorException;

    public Cliente find(String id) throws ClientErrorException;
    
    public List<Cliente> findCliente() throws ClientErrorException;

    public Reserva findReserva(String id) throws ClientErrorException;

    public void create(Object requestEntity) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public void close();
}