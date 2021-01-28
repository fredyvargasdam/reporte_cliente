/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import applicationClient.ApplicationClient;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Producto;
import modelo.Reserva;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservaTest extends ApplicationTest {
    
    private TableView table;
    private TableColumn<Reserva,Integer> Cantidad;
    private TextField buscar;
    private Button insertar;
    private Button borrar;
    private ComboBox productos;
    private ComboBox clientes;
    private ComboBox estado;
    
    /**
     * Inicia la aplicacion para testearla
     *
     * @param stage, Objeto Stage
     * @throws Exception, si ocurre algun error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationClient().start(stage);
        table=lookup("tbReservas").queryTableView();
        buscar=lookup("tfBuscar").query();
        insertar=lookup("btnInsertar").query();
        borrar=lookup("btnBorrar").query();
        productos=lookup("#clienteBox").queryComboBox();
        clientes=lookup("#productoBox").queryComboBox();
        estado=lookup("#tcEstado").queryComboBox();
    }
    
    /**
     * Comienza la ventana LogIn, introducimos las credenciales de
     * un vendedor para acceder a las reservas.
     */
    @Test
    public void preTest() {
        //Esperamos 3 segundos para que se pueda prepara el robot 
        sleep(3000);
        //Hacemos click en el TextField de usuario
        clickOn("#txtUsuario");
        //Escribimos el usuario: vendedor
        write("vendedor");
        //Hacemos click en el TextField de contraseña
        clickOn("#txtContrasena");
        //Escribimos la contraseña: vendedor
        write("vendedor");
        //Hacemos click en el botón de iniciar
        clickOn("#btnIniciar");
        //Comprobamos que la ventana de InicioVendedor sea visible
        verifyThat("#apInicioVendedor", isVisible());
        //Hacemos click en la opción de menu de mReservas
        clickOn("#mReservas");
        //Hacemos click en el menuItem de miGestionarReservas
        clickOn("#miGestionarReservas");
        //Comprobamos que la ventana de Reservas sea visible
        verifyThat("#pnReserva", isVisible());
    }
    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_Iniciar() {
        verifyThat("#tfBuscar",  hasText(""));
        verifyThat("#btnInsertar", isDisabled());
        verifyThat("#btnBorrar",  isDisabled());
        verifyThat("#btnVolver",  isEnabled());
        verifyThat("#tbReservas", isVisible());
        verifyThat("#tcCliente", isVisible());
        verifyThat("#tcProducto", isVisible());
        verifyThat("#tcCantidad", isVisible());
        verifyThat("#tcDescripcion", isVisible());
        verifyThat("#tcEstado", isVisible());
        verifyThat("#tcFecha", isVisible());
        verifyThat("#tcReserva", isVisible());
        verifyThat("#tcEntrega", isVisible());

    }
    /**
     * Test que borra una reserva
     */
    @Test
    public void testB_Borrar() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");

    }
    /**
     * Test que cancela el borrado.
     */
    @Test
    public void testC_CancelBorrar() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Cancelar");
    }
    /**
     * Test que modifica la cantidad.
     */
    @Test
    public void testD_ModificarCatidad() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
       //Hacemos click en el botón de Alta Proveedor
        //Cantiad
        //Inidicamos que clicke en la fila 0 y en la columna Cantidad
        Node rowSeleccionarCantidad = lookup("#tcCantidad").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowSeleccionarCantidad);
        //Inidicamos que clicke en la fila 1 y en la columna Cantidad
        Node rowCantidad = lookup("#tcCantidad").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowCantidad);
        //Escribimos una cantidad
        write("5");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        
    } 
    
    /**
     * Test que permite añadir una nueva reserva respetando las validaciones de
     * las celdas
     */
    @Test
    public void testE_NuevaReserva() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        //Hacemos click en el botón de Insertar Reservas
        clickOn("#btnInsertar");
        //Cliente
        //Inidicamos que clicke en la fila 0 y en la columna del Cliente
        Node rowColumnaCliente = lookup("#tcCliente").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowColumnaCliente);
        //Inidicamos que clicke en la fila 1 y en la columna del Cliente
        Node rowCliente = lookup("#tcCliente").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowCliente);
        clickOn(rowCliente);
        //Elegimos una opcion
        this.push(KeyCode.DOWN);
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        ///Producto
        Node rowColumnaProducto = lookup("#tcProducto").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowColumnaProducto);
        //Inidicamos que clicke en la fila 1 y en la columna producto
        Node rowProducto = lookup("#tcProducto").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowProducto);
        clickOn(rowProducto);
        //Elegimos una opcion
        this.push(KeyCode.DOWN);
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Cantidad
        //Inidicamos que clicke en la fila 0 y en la columna Cantidad
        Node rowSeleccionarCantidad = lookup("#tcCantidad").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowSeleccionarCantidad);
        //Inidicamos que clicke en la fila 1 y en la columna Cantidad
        Node rowCantidad = lookup("#tcCantidad").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowCantidad);
        //Escribimos una cantidad
        write("5");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Descripcion
        //Inidicamos que clicke en la fila 0 y en la columna Descripcion
        Node rowColumnaDescripcion = lookup("#tcDescripcion").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowColumnaDescripcion);
        //Inidicamos que clicke en la fila 1 y en la columna Descripcion
        Node rowDescripcion = lookup("#tcDescripcion").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowDescripcion);
        //Escribimos un Descripcion que contenga espacios y tildes
        write("Chandal adidas vaporu muy boinico");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Estado
        //Inidicamos que clicke en la fila 0 y en la columna del Estado
        Node rowColumnaEstado = lookup("#tcEstado").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowColumnaEstado);
        //Inidicamos que clicke en la fila 1 y en la columna del Estado
        Node rowEstado = lookup("#tcEstado").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowEstado);
        clickOn(rowEstado);
        //Elegimos una opcion
        this.push(KeyCode.DOWN);
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
       
    }
    
    
    
    /**
     * Test para comprobar que al salir nos envia al LogIn
     * 
     */
    @Test
    public void testF_menuSalir() {
        //Hacemos click al menu Perfil
        clickOn("#menuPerfil");
        //Hacemos click Salir
        clickOn("#menuSalir");
        //Comprobamos que sale una alerta, muestra la opción de Aceptar o Cancelar.
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
        //Comprobamos que nos dirije hacia la Ventana del LogIn
        verifyThat("#pnPrincipal", isVisible());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


}