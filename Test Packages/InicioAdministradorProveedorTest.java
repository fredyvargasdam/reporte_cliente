/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import applicationClient.ApplicationClient;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 *
 * @author Lorena Cáceres Manuel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InicioAdministradorProveedorTest extends ApplicationTest {

    /**
     * Inicia la aplicacion para testearla
     *
     * @param stage, Objeto Stage
     * @throws Exception, si ocurre algun error
     */
    public void start(Stage stage) throws Exception {
        new ApplicationClient().start(stage);

    }

    /*
    @Test
    public void testA_InitialState() {
        clickOn("#txtUsuario");
        write("administrador");
        clickOn("#txtContrasena");
        write("administrador");
        clickOn("#btnIniciar");
        verifyThat("#pnInicioAdminVend", isVisible());
        clickOn("#menuProveedor");
        clickOn("#menuProveedores");
        verifyThat("#pnInicioAdminProv", isVisible());
    }*/

    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testB_showStage() {
        verifyThat("#tfBuscar", hasText(""));
        verifyThat("#btnBorrarProveedor", isDisabled());
        verifyThat("#btnAltaProveedor", isEnabled());

    }

    @Test
    public void testC_menuSalir() {
        clickOn("#menuPerfil");
        clickOn("#menuSalir");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#pnPrincipal", isVisible());
    }

    @Test
    public void testD_menuListaVendedores() {
        clickOn("#menuVendedor");
        clickOn("#menuVendedores");
        verifyThat("#pnInicioAdminVend", isVisible());
    }
    
    @Test
    public void testE_botonAlta(){
        clickOn("#btnAltaProveedor");
        //Node row = lookup("#columnAge").nth(rowIWantToClick).query(); clickOn(node);
        verifyThat("#btnBorrarProveedor", isEnabled());
        
    }
    
    @Test
    public void testD_botonBorrar(){
        
    }

}
