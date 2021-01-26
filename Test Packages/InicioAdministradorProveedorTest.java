
import applicationClient.ApplicationClient;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
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
    @BeforeClass
    public static void setUpClass() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationClient.class);

    }

    /**
     * Comienza la ventana LogIn, introducimos las credenciales del
     * administrador para poder acceder a la ventana
     * InicioAdministradorProveedor
     */
    @Test
    public void preTest() {
        sleep(3000);
        clickOn("#txtUsuario");
        write("admin");
        clickOn("#txtContrasena");
        write("admin");
        clickOn("#btnIniciar");
        verifyThat("#pnInicioAdminVend", isVisible());
        clickOn("#menuProveedor");
        clickOn("#menuProveedores");
        verifyThat("#pnInicioAdminProv", isVisible());
    }

    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_showStage() {
        verifyThat("#tfBuscar", hasText(""));
        verifyThat("#btnBorrarProveedor", isDisabled());
        verifyThat("#btnAltaProveedor", isEnabled());

    }

    @Test
    public void testB_comprobacionDataPicker() {
        Node rowFecha = lookup("#tcFechaAlta").nth(1).query();
        doubleClickOn(rowFecha);
        clickOn(1315, 275);
        clickOn("16");
    }

    @Test
    public void testC_NuevoProveedorConValidaciones() {
        clickOn("#btnAltaProveedor");
        Node rowOrdenNombre = lookup("#tcNombre").nth(0).query();
        clickOn(rowOrdenNombre);
        //Nombre
        Node rowNombre = lookup("#tcNombre").nth(1).query();
        doubleClickOn(rowNombre);
        write("Antón García");
        this.push(KeyCode.ENTER);
        ///Tipo producto
        //Node rowOrdenTipoProducto = lookup("#tcTipo").nth(0).query();
        //clickOn(rowOrdenTipoProducto);
        Node rowTipo = lookup("#tcTipo").nth(1).query();
        rowTipo.isFocused();
        doubleClickOn(rowTipo);
        clickOn(rowTipo);
        //Orden Empresa
        Node rowOrdenEmpresa = lookup("#tcEmpresa").nth(0).query();
        clickOn(rowOrdenEmpresa);
        //Empresa
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        doubleClickOn(rowEmpresa);
        write("Roxy");
        this.push(KeyCode.ENTER);
        //Orden Email
        Node rowOrdenEmail = lookup("#tcEmail").nth(0).query();
        clickOn(rowOrdenEmail);
        //Email
        Node rowEmail = lookup("#tcEmail").nth(1).query();
        doubleClickOn(rowEmail);
        write("roxy@gmail.com");
        this.push(KeyCode.ENTER);
        //Orden Teléfono
        Node rowOrdenTelefono = lookup("#tcTelefono").nth(0).query();
        clickOn(rowOrdenTelefono);
        //Teléfono
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        doubleClickOn(rowTelefono);
        write("615478520");
        this.push(KeyCode.ENTER);
        //Orden Descripción
        Node rowOrdenDescripcion = lookup("#tcDescripcion").nth(0).query();
        clickOn(rowOrdenDescripcion);
        //Descripción
        Node rowDescripcion = lookup("#tcDescripcion").nth(1).query();
        doubleClickOn(rowDescripcion);
        write("Producto de buena calidad y rápida entrega");
        this.push(KeyCode.ENTER);

    }

    @Test
    public void testD_botonBorrarAceptar() {
        Node row = lookup("#tcNombre").nth(1).query();
        clickOn(row);
        verifyThat("#btnBorrarProveedor", isEnabled());
        clickOn("#btnBorrarProveedor");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testE_botonBorrarCancelar() {
        Node row = lookup("#tcNombre").nth(1).query();
        clickOn(row);
        clickOn("#btnBorrarProveedor");
        verifyThat("#btnBorrarProveedor", isEnabled());
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Cancelar");
    }

    @Test
    public void testF_AlertaNombre() {
        clickOn("#btnAltaProveedor");
        Node rowOrdenarNombre = lookup("#tcNombre").nth(0).query();
        clickOn(rowOrdenarNombre);
        Node rowNombre = lookup("#tcNombre").nth(1).query();
        doubleClickOn(rowNombre);
        write(".");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testG_AlertaEmpresa() {
        Node rowOrdenarEmpresa = lookup("#tcEmpresa").nth(0).query();
        clickOn(rowOrdenarEmpresa);
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        doubleClickOn(rowEmpresa);
        write(".");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testH_AlertaEmail() {
        Node rowOrdenarEmail = lookup("#tcEmail").nth(0).query();
        clickOn(rowOrdenarEmail);
        Node rowEmail = lookup("#tcEmail").nth(1).query();
        doubleClickOn(rowEmail);
        write(".");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testI_AlertaTelefonoLargo() {
        Node rowOrdenarTelefono = lookup("#tcTelefono").nth(0).query();
        clickOn(rowOrdenarTelefono);
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        doubleClickOn(rowTelefono);
        write("615478520888");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testJ_AlertaTelefonoCorto() {
        Node rowOrdenarTelefono = lookup("#tcTelefono").nth(0).query();
        clickOn(rowOrdenarTelefono);
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        doubleClickOn(rowTelefono);
        write("6");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testK_AlertaDescripcion() {
        Node rowOrdenarDescripcion = lookup("#tcDescripcion").nth(0).query();
        clickOn(rowOrdenarDescripcion);
        Node rowDescripcion = lookup("#tcDescripcion").nth(1).query();
        doubleClickOn(rowDescripcion);
        write(".");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testL_ProveedorYaExiste() {
        clickOn("#btnAltaProveedor");
        Node rowOrdenarEmpresa = lookup("#tcEmpresa").nth(0).query();
        clickOn(rowOrdenarEmpresa);
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        doubleClickOn(rowEmpresa);
        write("Puma");
        this.push(KeyCode.ENTER);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testM_botonBorrarError() {
        clickOn("#btnBorrarProveedor");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void testN_menuListaVendedores() {
        clickOn("#menuVendedor");
        clickOn("#menuVendedores");
        verifyThat("#pnInicioAdminVend", isVisible());
    }

    @Test
    public void testO_menuSalir() {
        clickOn("#menuPerfil");
        clickOn("#menuSalir");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#pnPrincipal", isVisible());
    }

}
