/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import applicationClient.ApplicationClient;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 *
 * @author Moroni
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InicioAdministradorVendedorTest extends ApplicationTest {
    
    /**
     * Inicia la aplicacion para testearla
     *
     * @param stage, Objeto Stage
     * @throws Exception, si ocurre algun error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new ApplicationClient().start(stage);

    }
    
    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_InitialState() {
        verifyThat("#pnInicioAdminVend", isVisible());
        verifyThat("#txtBuscarVendedor", hasText(""));
        verifyThat("#btnBuscar", isDisabled());
        verifyThat("#btnAltaVendedor", isEnabled());
        verifyThat("#btnBorrarVendedor", isDisabled());

    }
    
    /**
     * Test que comprueba que la vista de InicioAdministrador_Vendedor es visible cuando se hace click
     * en el boton Iniciar y el usuario es un Administrador
     */
    @Test
    public void testB_LoginToInicioAdministradorVendedor() {
        clickOn("#txtUsuario");
        write("vendedor");
        clickOn("#txtContrasena");
        write("vendedor");
        clickOn("#btnIniciar");
        verifyThat("#apInicioVendedor", isVisible());
    }
    
    /**
     * Test que verifica el btnBuscar
     */
    @Test
    public void testC_buscarBoton() {
        clickOn("#txtBuscarVendedor");
        write("Gisela");
        verifyThat("#btnBuscar", isEnabled());

    }
    
    @Test
    public void testD_borrarBoton() {
        Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        verifyThat("#btnBorrarVendedor", isEnabled());

    }
    
    @Test
    public void testE_administradorMenu() {
        clickOn("#menuPerfil");
        clickOn("#menuAdministrador");
        clickOn("Aceptar");
        verifyThat("#pnInicioAdminVend", isVisible());

    }
    
    @Test
    public void testF_salirMenu() {
        clickOn("#menuPerfil");
        clickOn("#menuSalir");
        clickOn("Aceptar");
        verifyThat("#pnPrincipal", isVisible());

    }
    
    @Test
    public void testG_proveedoresMenu() {
        clickOn("#menuProveedor");
        clickOn("#menuProveedores");
        clickOn("Aceptar");
        verifyThat("#pnInicioAdminProv", isVisible());

    }
    
    @Test
    public void testH_altaBoton() {
        clickOn("#btnAltaVendedor");
        Node row=lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(0).query();
        doubleClickOn(row);
        write("Alba");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        row=lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(1).query();
        doubleClickOn(row);
        write("Alba Fernandez");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        row=lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(3).query();
        doubleClickOn(row);
        clickOn("ENABLED");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        row=lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(4).query();
        doubleClickOn(row);
        write("999888777");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        row=lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(5).query();
        doubleClickOn(row);
        write("1000");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

    }
    
    @Test
    public void testI_borrarBoton() {
        Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#btnBorrarVendedor");
        clickOn("Aceptar");

    }
}
