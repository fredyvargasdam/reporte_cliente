/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import exceptions.InsertException;
import exceptions.UpdateException;
import exceptions.VendedorNotFoundException;
import exceptions.VendedorYaExisteException;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import modelo.Proveedor;
import modelo.Reserva;
import modelo.Vendedor;

/**
 *
 * @author Moroni
 */
public interface VendedorManager {

    public void edit(Vendedor vendedor) throws ClientErrorException, UpdateException, ErrorBDException, ErrorServerException, VendedorNotFoundException;

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException;

    public void create(Vendedor vendedor) throws ClientErrorException, InsertException, VendedorYaExisteException, ErrorBDException, ErrorServerException;

    public List<Reserva>  findAllReservas() throws ErrorServerException;

    public List<Vendedor> findAllVendedores() throws ErrorServerException;

     public List<Proveedor>  getProveedoresProducto() throws ErrorServerException ;

    public void remove(String id) throws ClientErrorException;

    public void close();

}
