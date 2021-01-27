package manager;

import exceptions.DeleteException;
import exceptions.ErrorServerException;
import exceptions.InsertException;
import exceptions.ProveedorNotFoundException;
import exceptions.SelectException;
import exceptions.UpdateException;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import modelo.Proveedor;
import modelo.Vendedor;

/**
 *
 * @author 2dam
 */
public interface AdministradorManager {

    public List<Vendedor> getVendedores() throws ClientErrorException, ErrorServerException;

    public void edit(Object requestEntity) throws ClientErrorException, UpdateException, ErrorServerException;

    public List<Proveedor> getProveedores() throws ClientErrorException, ErrorServerException;

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException, SelectException, ErrorServerException;

    public void create(Object requestEntity) throws ClientErrorException, InsertException, ErrorServerException;

    public void remove(String id) throws ClientErrorException, ProveedorNotFoundException, DeleteException, ErrorServerException;

    public void close();

}
