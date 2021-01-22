package manager;

import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import modelo.Proveedor;

/**
 *
 * @author Lorena Cáceres Manuel
 */
public interface AdministradorManager {

    public <T> T getVendedores(Class<T> responseType) throws ClientErrorException;

    public void edit(Object requestEntity) throws ClientErrorException, ErrorBDException, ErrorServerException;

    public List<Proveedor> getProveedores() throws ClientErrorException, ErrorBDException, ErrorServerException;

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException, ErrorBDException, ErrorServerException;

    public void create(Object requestEntity) throws ClientErrorException, ErrorBDException, ErrorServerException;

    public void remove(String id) throws ClientErrorException, ErrorBDException, ErrorServerException;

    public void close();

}
