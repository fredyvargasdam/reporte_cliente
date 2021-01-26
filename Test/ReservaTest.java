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
     * Test que permite ver el estado inicial de la ventana
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
    
    @Test
    public void testC_CancelBorrar() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Cancelar");
    }
    /*
    @Test
    public void testD_Modificar() {
        int rowCount=table.getItems().size();
        assertNotEquals("La tabla no tiene datos, imposible testearlo", rowCount,0);
        Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("la fila null: la tabla no tiene esa fila ",row);
        clickOn(row);
        
        doubleClickOn("#tcCantidad");
        write("5");
        this.push(KeyCode.ENTER);
        clickOn("#btnInsertar");
        doubleClickOn();
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        
    } */
  
    @Test
    public void testE_BotonesDisponibles() {
        Node row= lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        verifyThat("#btnInsertar", NodeMatchers.isEnabled());
        verifyThat("#btnBorrar", NodeMatchers.isEnabled());
        this.push(KeyCode.ALT, KeyCode.F4);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


}
