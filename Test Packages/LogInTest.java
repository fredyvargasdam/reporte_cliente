import applicationClient.ApplicationClient;

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
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 *
 * @author Lorena Cáceres Manuel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInTest extends ApplicationTest {

    public String num = "123456789123456789123456789123456789123456789123456789123456789";

    /**
     * Inicia la aplicacion para testearla
     *
     * @param stage, Objeto Stage
     * @throws Exception, si ocurre algun error
     */
    public void start(Stage stage) throws Exception {
        new ApplicationClient().start(stage);
    }

    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_InitialState() {
        verifyThat("#txtUsuario", hasText(""));
        verifyThat("#txtUsuario", isFocused());
        verifyThat("#txtContrasena", hasText(""));
        verifyThat("#btnIniciar", isDisabled());
        verifyThat("#hlRegistrarse", isEnabled());
        verifyThat("#hlContraseniaOlvidada",isInvisible());

    }

    /**
     * Test que prueba que el boton Iniciar esta habilitado cuando los campos
     * esten rellenos
     */
    @Test
    public void testB_IniciarIsEnabled() {
        clickOn("#txtUsuario");
        write("username");
        clickOn("#txtContrasena");
        write("password");
        verifyThat("#btnIniciar", isEnabled());
    }

    /**
     * Test que prueba que el boton Iniciar esta deshabilitado cuando uno de los
     * campos estan vacios
     */
    @Test
    public void testC_IniciarIsDisabled() {
        clickOn("#txtUsuario");
        write("username");
        verifyThat("#btnIniciar", isDisabled());
        eraseText(8);
        clickOn("#txtContrasena");
        write("password");
        eraseText(8);
        verifyThat("#btnIniciar", isDisabled());
    }

    /**
     * Test que prueba la longitud maxima de los campos
     */
    @Test
    public void testD_maxLenght() {
        clickOn("#txtUsuario");
        write(num);
        eraseText(30);
        clickOn("#txtContrasena");
        write(num);
        eraseText(30);
        verifyThat("#btnIniciar", isDisabled());
    }

    /**
     * Test que comprueba que la vista de InicioAdministrador_Vendedor es visible cuando se hace click
     * en el boton Iniciar y el usuario es un Administrador
     */
    @Test
    public void testE_LoginToInicioAdministradorVendedor() {
        clickOn("#txtUsuario");
        write("vendedor");
        clickOn("#txtContrasena");
        write("vendedor");
        clickOn("#btnIniciar");
        verifyThat("#apInicioVendedor", isVisible());
    }

    /**
     * Test que comprueba que la vista del InicioVendedorProducto es visible cuando se hace click
     * en el boton Iniciar y el usuario es un Vendedor
     */
    @Test
    public void testF_LoginToInicioVendedorProducto() {
        clickOn("#txtUsuario");
        write("Administrador");
        clickOn("#txtContrasena");
        write("admin");
        clickOn("#btnIniciar");
        verifyThat("#pnInicioAdminVend", isVisible());
    }

    /**
     * Test que comprueba que la vista del SignUp es visible cuando se hace click
     * en el botón Registrar 
     */
    @Test
    public void testG_LoginToSignUp() {
        clickOn("#hlRegistrarse");
        verifyThat("#apRegistro", isVisible());
    }

    /**
     * Test que comprueba que la vista de RecuperarContraseña es visible cuando se introduce
     * un usuario y la contraseña es incorrecta, aparecerá un hyperlink que nos 
     * muestra la ventana RecuperarContrasena
     */
    @Test
    public void testH_contrasenaIncorrecta() {
        clickOn("#txtUsuario");
        write("kenay26");
        clickOn("#txtContrasena");
        write("1234");
        clickOn("#btnIniciar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clickOn("#hlContraseniaOlvidada");
        verifyThat("#apRecuperarContrasena", isVisible());
    }

}
