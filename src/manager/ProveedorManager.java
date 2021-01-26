package manager;

import exceptions.ErrorServerException;
import exceptions.ProductoNotFoundException;
import exceptions.ProveedorNotFoundException;
import exceptions.ProveedorYaExisteException;
import javax.ws.rs.ClientErrorException;
import modelo.Proveedor;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public interface ProveedorManager {

    public void edit(Proveedor proveedor) throws ClientErrorException, ErrorServerException ;

    public Proveedor find(Proveedor proveedor, String id) throws ClientErrorException, ProveedorNotFoundException, ErrorServerException;

    public Proveedor getProductos(Proveedor proveedor, String id) throws ErrorServerException, ProductoNotFoundException;

    public void create(Proveedor proveedor) throws ClientErrorException, ProveedorYaExisteException, ErrorServerException;

    public void remove(String id) throws ClientErrorException, ErrorServerException;

    public void close();

}
