/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.VendedorRESTClient;
import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import exceptions.InsertException;
import exceptions.ProveedorNotFoundException;
import exceptions.SelectException;
import exceptions.UpdateException;
import exceptions.VendedorNotFoundException;
import exceptions.VendedorYaExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import manager.VendedorManager;
import modelo.Proveedor;
import modelo.Reserva;
import modelo.Vendedor;

/**
 *
 * @author Moroni, Fredy Vargas Flores
 */
public class VendedorManagerImplementation implements VendedorManager {

    private final VendedorRESTClient webClient;
    private static final Logger LOG = Logger.getLogger("VendedorManagerImplementation");

    public VendedorManagerImplementation() {
        webClient = new VendedorRESTClient();
    }

    @Override
    public void remove(String id) throws ClientErrorException {
        try {
            // webClient = new VendedorRESTClient();
            webClient.remove(id);
        } catch (ClientErrorException e) {
            LOG.log(Level.SEVERE, "ClientErrorException");
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param vendedor
     * @throws ClientErrorException
     * @throws UpdateException
     * @throws ErrorBDException
     * @throws ErrorServerException
     * @throws ProveedorNotFoundException
     */
    @Override
    public void edit(Vendedor vendedor) throws ClientErrorException, UpdateException, ErrorBDException, ErrorServerException, VendedorNotFoundException {
     //   webClient = new VendedorRESTClient();
        webClient.edit(vendedor);
    }

    public Vendedor find(Vendedor vendedor, String id) throws ClientErrorException, SelectException, ProveedorNotFoundException, ErrorBDException, ErrorServerException {
        vendedor = null;
        try {
            vendedor = webClient.find(Vendedor.class, id);
        } catch (ClientErrorException e) {
            LOG.log(Level.SEVERE, "ClientErrorException");
        }
        return vendedor;
    }

    /**
     *
     * @param vendedor
     * @throws ClientErrorException
     * @throws InsertException
     * @throws VendedorYaExisteException
     * @throws ErrorBDException
     * @throws ErrorServerException
     */
    @Override
    public void create(Vendedor vendedor) throws ClientErrorException, InsertException, VendedorYaExisteException, ErrorBDException, ErrorServerException {
     //   webClient = new VendedorRESTClient();
        webClient.create(vendedor);
    }

    @Override
    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vendedor> findAllVendedores() throws ErrorServerException {
        List<Vendedor> vendedores = null;
        try {
            vendedores = webClient.findAllVendedores(new GenericType<List<Vendedor>>() {
            });

        } catch (ClientErrorException e) {
            LOG.severe("VendedorManagerImplementacion: findAllVendedores " + e.getMessage());
            throw new ErrorServerException();
        }
        return vendedores;
    }

    @Override
    public List<Reserva> findAllReservas() throws ErrorServerException {
        List<Reserva> reservas = null;
        try {
            reservas = webClient.findAllReservas(new GenericType<List<Reserva>>() {
            });

        } catch (ClientErrorException e) {
            LOG.severe("VendedorManagerImplementacion: findAllReservas " + e.getMessage());
            throw new ErrorServerException();
        }
        return reservas;
    }

    @Override
    public List<Proveedor> getProveedoresProducto() throws ErrorServerException {
        List<Proveedor> proveedores = null;
        try {
            proveedores = webClient.getProveedoresProducto(new GenericType<List<Proveedor>>() {
            });
        } catch (Exception e) {
            LOG.severe("VendedorManagerImplementacion: getProveedoresProducto " + e.getMessage());
            throw new ErrorServerException();
        }
        return proveedores;
    }

}
