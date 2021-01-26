/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import applicationClient.ApplicationClient;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InicioVendedorProductoTest extends ApplicationTest {

    private TableView table;
    private ChoiceBox tallas;
    private DatePicker dpDisponibilidad;
    private Button btnNuevo;
    private Button btnBorrar;
    private Menu mProductos;
    private Menu mReservas;
    private MenuItem miSalir;
    private MenuItem miGestionarReservas;

    @Override
    public void start(Stage stage) throws Exception {
        //start JavaFX application to be tested    
        new ApplicationClient().start(stage);
        table = lookup("#tvProductos").queryTableView();
        btnNuevo = lookup("#btnNuevo").query();
        btnBorrar = lookup("#btnBorrar").query();

    }

    /**
     * Inicia Sesión
     */
    //  @Test
    public void testA_IniciarIsEnabled() {
        clickOn("#txtUsuario");
        write("pepe");
        clickOn("#txtContrasena");
        write("abcd");
        clickOn("#btnIniciar");
    }

    /**
     * Verificar que la ventana de productos este con los valores establecidos
     */
    //  @Test
    public void testB_IniciarVentana() {
        // verifyThat("#tfBuscar", hasText(""));
        verifyThat(btnBorrar, isDisabled());
        verifyThat(btnNuevo, isEnabled());

    }

    /**
     * Comprobar que el boton nuevo crea una nueva celda y el boton borrar este
     * habilitado
     */
    // @Test
    public void testD_NuevaCeldaTableView() {
        //verificar que la tabla tiene filas
        assertNotEquals("Table has no data: Cannot test.",
                table.getItems().size(), 0);
        clickOn(btnNuevo);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat(btnBorrar, isEnabled());
    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Fácil)
     */
    //@Test
    public void testE_BorrarCelda() {
        int rowCount = table.getItems().size();
        Node row = lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        assertEquals("La fila ha sido borrada con exito", rowCount, table.getItems().size() + 1);
    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Medio)
     */
      @Test
    public void testF_BorrarCelda() {
        int rowCount = table.getItems().size();
        Node row = lookup(".table-row-cell").nth(5).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        assertEquals("La fila se ha modificado a un stock 0", rowCount, table.getItems().size());
        //Aqui es cuando el producto esta reservado y pones el stock a 0        
    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Forzado)
     */
    // @Test
    public void testG_BorrarCelda() {
        Node row = lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        //Aqui el stock es 
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");

    }

}
